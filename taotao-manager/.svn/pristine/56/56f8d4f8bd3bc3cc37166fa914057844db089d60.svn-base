package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.TbContentService;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2020年2月23日 下午8:47:59 
* 类说明 
*/

@Controller
public class TbContentController {

	@Autowired
	public TbContentService contentService;
	
	/**
	 * 初始化内容管理页面并显示功能
	 * <p>Title: getTbContentList</p>
	 * <p>Description: </p>
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EUDataGridResult getTbContentList(Long categoryId, int page, int rows){
		EUDataGridResult result = contentService.getTbContentList(categoryId, page, rows);
		return result;
		
	}
	
	/**
	 * 新增功能
	 * <p>Title: insertTbContent</p>
	 * <p>Description: </p>
	 * @param tbContent
	 * @return
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insertTbContent(TbContent tbContent){
		TaotaoResult result = contentService.insertTbContent(tbContent);
		return result;
	}
}
