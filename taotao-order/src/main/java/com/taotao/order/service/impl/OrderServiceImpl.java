package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/** 
 * 
* @description:create order
* @author: hlv
* @date：2020年4月15日 上午11:59:00 
* 类说明 
*/

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	
	@Value("${ORDER_NO_INIT_KEY}")
	private String ORDER_NO_INIT_KEY;
	
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;
	
	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> orderItemList, TbOrderShipping orderShipping) {
		// 订单表插记录
		String stringOrder = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(stringOrder)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_NO_INIT_KEY);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		// 补全pojo属性
		order.setOrderId(orderId + ""); 
		// '状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		// //0：未评价 1：已评价
		order.setBuyerRate(0);
		// 向订单表插入明细
		orderMapper.insert(order);
		
		// 向订单明细表插入数据
		for (TbOrderItem tbOrderItem : orderItemList) {
			// 补全订单明细
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId + "");
			tbOrderItem.setOrderId(orderId + "");
			orderItemMapper.insert(tbOrderItem);
		}
		
		// 插入物流表信息
		orderShipping.setOrderId(orderId + ""); 
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}

}
