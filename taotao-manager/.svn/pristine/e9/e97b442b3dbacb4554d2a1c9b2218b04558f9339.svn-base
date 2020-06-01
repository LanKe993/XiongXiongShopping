package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.TbContentService;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2020年2月23日 下午8:28:29 
* 类说明 
*/

@Service
public class TbContentServiceImpl implements TbContentService{

	@Autowired
	public TbContentMapper tbContentMapper;
	
	@Override
	public EUDataGridResult getTbContentList(long categoryId, int page, int rows) {
		
		TbContentExample tbContentExample = new TbContentExample();
		// 分页查询
		PageHelper.startPage(page, rows);
		// 创建查询条件
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);
		
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 新增功能
	 * <p>Title: insertTbContent</p>
	 * <p>Description: </p>
	 * @param tbContent
	 * @return
	 * @see com.taotao.service.TbContentService#insertTbContent(com.taotao.pojo.TbContent)
	 */
	@Override
	public TaotaoResult insertTbContent(TbContent tbContent) {
		// 设置表单中没有的数据库元素
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		
		// 插入数据
		tbContentMapper.insert(tbContent);
		return TaotaoResult.ok();
	}
}