package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @description:
* @author: hlv
* @date：2020年4月9日 上午11:58:18 
* 类说明 
*/

@Controller
@RequestMapping("/page")
public class PageController {
	
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
