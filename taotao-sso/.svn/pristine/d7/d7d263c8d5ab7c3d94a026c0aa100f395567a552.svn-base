package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/** 
* @description:
* @author: hlv
* @date：2020年4月7日 下午4:52:51 
* 类说明 
*/

public interface UserService {

	// 检查用户信息是否存在
	TaotaoResult checkData(String content, Integer type);
	
	// 用户注册
	TaotaoResult createtUser(TbUser tbUser);
	
	// 用户登录
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	
	// 通过token查询用户信息
	TaotaoResult getUserDataByToken(String token);
	
	// 安全退出
	TaotaoResult safetyLogout(String token);
}
