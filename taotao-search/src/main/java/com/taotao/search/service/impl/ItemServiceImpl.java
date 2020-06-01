package com.taotao.search.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

/** 
* @description:
* @author: hlv
* @date：2020年3月25日 下午4:51:36 
* 类说明 
*/

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult importAllItems() {
		
		try {
			
			// 查询商品列表
			List<Item> list = itemMapper.getItemList();
			
			// 把商品写入索引库
			for (Item item : list) {
				// 创建一个solrInputDocunment对象
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", item.getId());
				document.addField("item_title", item.getTitle());
				document.addField("item_sell_point", item.getSell_point());
				document.addField("item_price", item.getPrice());
				document.addField("item_image", item.getImage());
				document.addField("item_category_name", item.getCategory_name());
				document.addField("item_desc", item.getItem_desc());
				
				// 写入索引库
				solrServer.add(document);
				solrServer.commit();
			}
				return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	/*
	 * CMS新增商品时就将相关搜索数据插入到solr索引库中
	 */
	@Override
	public TaotaoResult importSingleItemData(HashMap<String, String> singleItemData) {
		
		try {
			
			// 将商品写入索引库
			// 创建一个solrInputDocument对象
			SolrInputDocument inputDocument = new SolrInputDocument();
			inputDocument.addField("id", 					singleItemData.get("id"));
			inputDocument.addField("item_title", 			new String(singleItemData.get("item_title").getBytes("iso8859-1"), "utf-8"));
			inputDocument.addField("item_sell_point", 		singleItemData.get("item_sell_point"));
			inputDocument.addField("item_price", 			singleItemData.get("item_price"));
			inputDocument.addField("item_image", 			singleItemData.get("item_image"));
			inputDocument.addField("item_category_name", 	singleItemData.get("item_category_name"));
			inputDocument.addField("item_desc", 			singleItemData.get("item_desc"));

			// 写入索引库
			solrServer.add(inputDocument);
			solrServer.commit();
			
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	
//	@Override
//	public TaotaoResult importSingleItemData(String singleItemData) {
//		
//		try {
//			
//			// 将商品写入索引库
//			Item item = JsonUtils.jsonToPojo(singleItemData, Item.class);
//			// 创建一个solrInputDocument对象
//			SolrInputDocument inputDocument = new SolrInputDocument();
//			inputDocument.addField("id", 					item.getId());
//			inputDocument.addField("item_title", 			item.getTitle());
//			inputDocument.addField("item_sell_point", 		item.getSell_point());
//			inputDocument.addField("item_price", 			item.getPrice());
//			inputDocument.addField("item_image", 			item.getImage());
//			inputDocument.addField("item_category_name", 	item.getCategory_name());
//			inputDocument.addField("item_desc", 			item.getItem_desc());
//
//			// 写入索引库
//			solrServer.add(inputDocument);
//			solrServer.commit();
//			
//			return TaotaoResult.ok();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
//		}
//	}
}