package com.taotao.sso.dao;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2020年3月20日 下午4:34:28 
* 类说明 
*/

public interface JedisClient {

	String get(String key);
	
	String set(String key, String value);
	
	String hget(String hkey, String key);
	
	long hset(String hkey, String key, String value);
	
	long incr(String key);
	
	long expire(String key, int second);
	
	long ttl(String key);
	
	long del(String key);
	
	long hdel(String hkey, String key);
}
