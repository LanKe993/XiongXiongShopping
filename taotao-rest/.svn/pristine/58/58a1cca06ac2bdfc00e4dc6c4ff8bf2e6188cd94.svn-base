package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类列表展示 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月28日 下午2:25:27 
* 类说明 
*/

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	// 方法一
	/*@RequestMapping(value="/itemcat/list", produces=MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback){
		CatResult catResult = itemCatService.getItemCatList();
		// 把pojo转为字符串
		String json = JsonUtils.objectToJson(catResult);
		// 拼装返回值
		String result = callback + "(" + json + ");";
		
		return result;
	}*/
	
	// 方法二
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback){
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue jacksonValue = new MappingJacksonValue(catResult);
		jacksonValue.setJsonpFunction(callback);
		return jacksonValue;
		
	}
}
