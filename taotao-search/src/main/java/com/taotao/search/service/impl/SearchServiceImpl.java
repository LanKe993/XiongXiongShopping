package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/** 
* @description:搜索service
* @author: hlv
* @date：2020年3月26日 下午10:36:33 
* 类说明 
*/

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		
		// 创建查询对象
		SolrQuery solrQuery = new SolrQuery();
		
		// 执行查询
		solrQuery.setQuery(queryString);
		
		// 设置分页
		solrQuery.setStart((page - 1) * rows);
		solrQuery.setRows(rows);
		
		// 设置默认搜索域
		solrQuery.set("df", "item_keywords");
		
		// 设置高亮显示
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		
		// 执行查询
		SearchResult searchResult = searchDao.search(solrQuery);
		
		// 计算总页数
		long recordCount = searchResult.getRecordCount();
		long pageCount = recordCount / rows;
		if (recordCount % rows > 0) {
			pageCount ++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		
		return searchResult;
	}

}
