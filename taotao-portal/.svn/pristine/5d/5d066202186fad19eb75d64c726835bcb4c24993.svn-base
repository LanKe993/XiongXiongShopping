package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.service.ContentService;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月26日 下午6:53:49 
* 类说明 
*/

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model){
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		return "index";
	}
	
	@RequestMapping(value="httpclient/post", method=RequestMethod.POST)
	@ResponseBody
	public String testPost(){
		return "OK";
	}
}
