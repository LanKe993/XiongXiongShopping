package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;

/** 
* @description:查询商品DAO
* @author: hlv
* @date：2020年3月26日 下午12:32:27 
* 类说明 
*/

@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private SolrServer solrServer;
	@Override
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		// 返回值结果对象
		SearchResult result = new SearchResult();
		// 执行查询
		QueryResponse queryResponse = solrServer.query(solrQuery);
		
		// 高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		// 取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		
		// 取得查询结果总条数
		result.setRecordCount(solrDocumentList.getNumFound());
		
		// 商品列表
		List<Item> itemList = new ArrayList<>();
		
		for (SolrDocument solrDocument : solrDocumentList) {
			// 创建商品对象
			Item item = new Item();
			item.setId((String) solrDocument.get("id"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			// 高亮显示title
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			item.setTitle(title);
			
			// 添加到商品列表
			itemList.add(item);
			
		}
		result.setItemList(itemList);
		
		return result;
	}

}
