package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2020年2月23日 下午8:26:45 
* 类说明 
*/

public interface TbContentService {

	// 初始化内容管理页面并显示功能
	EUDataGridResult getTbContentList(long id, int page, int rows);
	
	// 新增功能
	TaotaoResult insertTbContent(TbContent tbContent);
	
}
