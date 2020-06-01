package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

/** 
* @description:
* @author: hlv
* @date：2020年3月29日 下午2:41:11 
* 类说明 
*/

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Override
	public TaotaoResult getItemBaseInfo(long itemId) {
		
		// 在redis中取商品的基本信息
		try {
			String stringJson = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			if (!StringUtils.isBlank(stringJson)) {
				// 把json转换为java对象
				TbItem item = JsonUtils.jsonToPojo(stringJson, TbItem.class);
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 取商品基本信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		// 插入缓存
		try {
			// 向redis中存商品基本信息
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			// 设置商品基本信息key过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 使用taotaoResult包装
		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDestInfo(long itemId) {
		
		// 在redis中取商品的基本信息
		try {
			String stringJson = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if (!StringUtils.isBlank(stringJson)) {
				// 把json转换为java对象
				TbItemDesc itemDest = JsonUtils.jsonToPojo(stringJson, TbItemDesc.class);
				return TaotaoResult.ok(itemDest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 查询商品描述信息
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		
		// 插入缓存
		try {
			// 向redis中存商品基本信息
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
			// 设置商品基本信息key过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 使用taotaoResult包装
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult getItemParamInfo(long itemId) {
		
		
		// 在redis中取商品的基本信息
		try {
			String stringJson = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
			if (!StringUtils.isBlank(stringJson)) {
				// 把json转换为java对象
				TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(stringJson, TbItemParamItem.class);
				return TaotaoResult.ok(itemParamItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 取商品规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem itemParamItem = list.get(0);
			
			// 插入缓存
			try {
				// 向redis中存商品基本信息
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(itemParamItem));
				// 设置商品基本信息key过期时间
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return TaotaoResult.ok(itemParamItem);
		}
		
		return TaotaoResult.build(400, "无此商品规格");
	}

}
