package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

/** 
* @description:
* @author: hlv
* @date：2020年4月11日 下午5:04:01 
* 类说明 
*/

public interface CartService {

	TaotaoResult addCartItem(long itemId, int number, HttpServletRequest request, HttpServletResponse response);
	
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	
	// 直接修改购物车商品数量
	TaotaoResult updateCartNumber(long itemId, int number, HttpServletRequest request, HttpServletResponse response);
	
	// 购物车删除商品
	TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
