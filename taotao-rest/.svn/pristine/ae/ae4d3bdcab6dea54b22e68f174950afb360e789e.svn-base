package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;

/** 
* @description:商品信息controller
* @author: hlv
* @date：2020年4月1日 下午2:51:33 
* 类说明 
*/

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	// 取商品基本信息
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	// 取商品描述信息
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDescInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemDestInfo(itemId);
		return result;
	}
	
	// 取商品规格信息
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemParamInfo(itemId);
		return result;
	}
}
