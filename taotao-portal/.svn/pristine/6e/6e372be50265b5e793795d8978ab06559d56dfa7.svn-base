package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/** 
* @description:商品基本信息描述
* @author: hlv
* @date：2020年4月2日 下午9:42:09 
* 类说明 
*/
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		ItemInfo item = itemService.getItemBaseInfoById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemDesc(@PathVariable Long itemId) {
		String result = itemService.getItemDestInfoById(itemId);
		return result;
	}
	
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String showItemParam(@PathVariable Long itemId) {
		String result = itemService.getItemParamInfoById(itemId);
		return result;
	}
}
