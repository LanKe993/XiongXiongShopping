package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

/** 
* @author 作者 Your-Name: hlv
* @version 创建时间：2019年12月14日 下午2:46:45 
* 类说明 
*/

public interface ContentCategoryService {

	// contentCategoryList，取得内容分类管理页面
	List<EUTreeNode> getCategoryList(long parentId);
	
	// 内容管理分类页面右键，添加功能
	TaotaoResult insertContentCategory(long parentId, String name);
	
	// 内容管理分类页面右键，删除功能(物理删除)
	TaotaoResult deleteContentCategory(long parentId, long id);
	
	// 内容管理分类页面右键，重命名功能
	TaotaoResult updateContentCategory(long id, String name);
}
