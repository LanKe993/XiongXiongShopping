package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2020年2月24日 下午9:44:07 
* 类说明 
*/

public interface ContentService {

	List<TbContent> getContentList(long contentCID);
}
