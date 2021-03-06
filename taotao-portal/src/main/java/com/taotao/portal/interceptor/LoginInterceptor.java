package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.impl.UserServiceImpl;

/** 
* @description:拦截器
* @author: hlv
* @date：2020年4月10日 下午9:23:04 
* 类说明 
*/

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 在handler执行之前处理
		// 判断用户是否登录
		// 从cookice中取token
		String tokenValue = CookieUtils.getCookieValue(request, "TT_TOKEN");
		// 根据token取得用户信息，调用sso系统接口
		TbUser user = userServiceImpl.getUserDataByToken(tokenValue);
		// 如果取不到用户信息,跳转到登录页面
		if (null == user) {
			// 跳转到登录页面
			response.sendRedirect(userServiceImpl.SSO_BASE_URL + userServiceImpl.SSO_PAGE_URL + "?redirect=" + request.getRequestURL());
			return false;
		}
		
		// 取的用户信息，放行 
		request.setAttribute("user", user);
		
		// 返回值决定handler是否执行，true：执行， false：不执行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回modelandview之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回modelandviewer之后

	}

}
