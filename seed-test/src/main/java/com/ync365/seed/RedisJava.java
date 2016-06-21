package com.ync365.seed;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisJava {
	
	public static void main(String[] args) {
		//连接本地的redis服务
		Jedis jedis=new Jedis("localhost");
		System.out.println("connection  to  server sucessfully");
		System.out.println("server is "+ jedis.ping());
		//String
		jedis.set("w3ckey", "redis tutorial");
		System.out.println("stored string in redis:" + jedis.get("w3ckey"));
		//list
		jedis.lpush("tutorial-list", "redis01");
		jedis.lpush("tutorial-list", "redis02");
		jedis.lpush("tutorial-list", "redis03");
		List<String> list=jedis.lrange("tutorial-list", 0, 5);
		for(int i=0;i<list.size();i++){
			System.out.println("Stored string in redis :"+list.get(i));
		}
		
		
	}
}
	