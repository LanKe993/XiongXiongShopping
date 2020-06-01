package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/** 
 * 内容分类管理
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月14日 下午3:51:05 
* 类说明 
*/
@Controller		 
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	// 内容分类列表遍历
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId){
		List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}
	
	// 右键新增
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(long parentId, String name){
		TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	// 右键删除
	@RequestMapping("/delete/")
	@ResponseBody
	public TaotaoResult deleteContentCategory(long parentId, long id){
		TaotaoResult result = contentCategoryService.deleteContentCategory(parentId, id);
		return result;
	}
	
	// 右键修改
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(long id, String name){
		TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
}
