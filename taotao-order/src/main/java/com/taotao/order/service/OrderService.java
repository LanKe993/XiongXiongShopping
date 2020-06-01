package com.taotao.order.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/** 
* @description:
* @author: hlv
* @date：2020年4月15日 上午11:57:18 
* 类说明 
*/

public interface OrderService {

	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> orderItemList, TbOrderShipping orderShipping);
}
