package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**页面跳转controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2019年8月1日下午4:24:17
 * @version 1.0
 */
@Controller
public class PageController {

	/*
	 * 打开首页
	 */
	@RequestMapping("/")
	public  String showIndex() {
		return "index";
	}
	
	/**
	 * 展示其他商品
	 * <p>Title: PageController</p>
	 * <p>Description: </p>
	 * <p>Company: www.itcast.com</p> 
	 * @author	入云龙
	 * @date	2019年8月2日下午5:33:02
	 * @version 1.0
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
