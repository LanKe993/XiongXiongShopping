package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/** 
* @description:
* @author: hlv
* @date：2020年4月10日 下午9:34:08 
* 类说明 
*/

@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_GET_USER_DATA_BY_TOKEN}")
	private String SSO_GET_USER_DATA_BY_TOKEN;
	
	@Value("${SSO_PAGE_URL}")
	public String SSO_PAGE_URL;
	
	@Override
	public TbUser getUserDataByToken(String token) {
		
		try {
			// 根据token取用户信息
			String userDataJson = HttpClientUtil.doGet(SSO_BASE_URL + SSO_GET_USER_DATA_BY_TOKEN + token);
			// 把json数据转为taotaoresult对象
			TaotaoResult result = TaotaoResult.formatToPojo(userDataJson, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
