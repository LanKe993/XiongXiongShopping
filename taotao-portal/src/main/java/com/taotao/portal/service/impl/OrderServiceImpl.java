package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;

/** 
* @description:
* @author: hlv
* @date：2020年4月16日 下午9:28:44 
* 类说明 
*/

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	@Value("${CREATE_ORDER_URL}")
	private String CREATE_ORDER_URL;
	
	@Override
	public String createOrder(Order order) {
		// 调用taotao-order的服务提交订单
		String doPostJson = HttpClientUtil.doPostJson(ORDER_BASE_URL + CREATE_ORDER_URL, JsonUtils.objectToJson(order));
		TaotaoResult taotaoResult = TaotaoResult.format(doPostJson);
		if (taotaoResult.getStatus() == 200) {
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}

}
