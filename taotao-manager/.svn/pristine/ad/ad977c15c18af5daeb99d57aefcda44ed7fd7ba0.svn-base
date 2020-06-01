package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/** 
 * 内容分类管理
* @author 作者 Your-Name: 
* @version 创建时间：2019年12月14日 下午2:48:46 
* 类说明 
*/

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		// 根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		// 创建节点list
		List<EUTreeNode> resultList =  new ArrayList<>();
		for (TbContentCategory tbContentCategory : list){
			// 创建一个节点
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setParentId(tbContentCategory.getParentId());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			resultList.add(node);
		}
		
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		// 创建一个pojo
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setIsParent(false);
		// 1(正常),2(删除)',
		tbContentCategory.setStatus(1);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		
		// 添加记录
		contentCategoryMapper.insert(tbContentCategory);
		
		// 查看父节点的isParent列是否为true，不为true更新为true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		// 判断是否为true
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			// 更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		// 返回结果
		return TaotaoResult.ok(tbContentCategory);
	}

	@Override
	public TaotaoResult deleteContentCategory(long parentId, long id) {

		// 根据主键删除数据
		contentCategoryMapper.deleteByPrimaryKey(id);
		// 根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		// 判断删除节点的父节点是否还有子节点，如果没有，将父节点isParent列修改为false
		if(list.size() <= 0){
			TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
			// 判断是否为true
			if (parentCat.getIsParent()) {
				parentCat.setIsParent(false);
				// 更新父节点
				contentCategoryMapper.updateByPrimaryKey(parentCat);
			}
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		// 设置更新条件
		TbContentCategory record = new TbContentCategory();
		record.setName(name);
		record.setUpdated(new Date());
		record.setId(id);
		contentCategoryMapper.updateByPrimaryKeySelective(record);
		return TaotaoResult.ok();
	}

}
