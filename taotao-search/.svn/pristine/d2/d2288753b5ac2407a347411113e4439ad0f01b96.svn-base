package com.taotao.search.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;

/** 
* @description:索引库维护
* @author: hlv
* @date：2020年3月25日 下午5:42:49 
* 类说明 
*/
@Controller
@RequestMapping("/manager")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/*
	 * import item cat into index database;
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAllItems() {
		TaotaoResult result = itemService.importAllItems();
		return result;
	}
	
	/*
	 * http://localhost:8083/search/manager/importall
	 * import item cat into solr database bu itemId;
	 */
	@RequestMapping(value="/importSingleItem", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public TaotaoResult importSingleItemData(@RequestParam HashMap<String, String> singleItemData) {
		TaotaoResult result = itemService.importSingleItemData(singleItemData);
		return result;
	}
}
