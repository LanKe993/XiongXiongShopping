package com.taotao.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2020年3月20日 下午5:09:15 
* 类说明 
*/

public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long hset = jedis.hset(hkey, key, value);
		jedis.close();
		return hset;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		long incr = jedis.incr(key);
		jedis.close();
		return incr;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		long expire = jedis.expire(key, second);
		jedis.close();
		return expire;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		long ttl = jedis.ttl(key);
		jedis.close();
		return ttl;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		long del = jedis.del(key);
		jedis.close();
		return del;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		long hdel = jedis.hdel(hkey, key);
		jedis.close();
		return hdel;
	}

}
