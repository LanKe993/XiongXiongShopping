package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/** 
 * 商品分类服务
* @author 作者 Your-Name: 
* @version 创建时间：2019年11月27日 下午6:33:07 
* 类说明 
*/

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired 
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_ITEM_CAT_REDIS_KEY}")
	private String INDEX_ITEM_CAT_REDIS_KEY;
	
	@Override
	public CatResult getItemCatList() {

		CatResult catResult = new CatResult();
		
		// 取缓存
		try {
			String getCarString = jedisClient.hget(INDEX_ITEM_CAT_REDIS_KEY, 0 + "");
			if (!StringUtils.isEmpty(getCarString)) {
				catResult = (CatResult) JsonUtils.jsonToPojo(getCarString, CatResult.class);
				return catResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 查询分类列表
		catResult.setData(getCatList(0));
		
		// 添加缓存
		try {
			String resultListString = JsonUtils.objectToJson(catResult);
			jedisClient.hset(INDEX_ITEM_CAT_REDIS_KEY, 0 + "", resultListString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return catResult;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<?> getCatList(long parentId){
		// 返回值list
		List resultList = new ArrayList<>();
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		int count = 0 ;
		for(TbItemCat tbItemCat : list){
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				
				CatNode catNode = new CatNode();
				catNode.setUrl("/products/"+ tbItemCat.getId() +".html");
				if(parentId == 0){
					catNode.setName("<a href='/products/"+ tbItemCat.getId() +".html'>"+ tbItemCat.getName() +"</a>");
				}else{
					catNode.setName(tbItemCat.getName());
				}
				catNode.setItem(getCatList(tbItemCat.getId()));
				
				resultList.add(catNode);
				count ++;
				if (count >= 14) {
					break;
				}
			// 判断叶子节点
			}else {
				resultList.add("/products/"+ tbItemCat.getId() +".html|" + tbItemCat.getName()); 
			}
		}
		
		return resultList;
	}


}
