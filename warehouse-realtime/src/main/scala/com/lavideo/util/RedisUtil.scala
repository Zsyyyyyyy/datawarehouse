package com.lavideo.util

import java.util.Properties

import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

object RedisUtil {
    var jedisPool : JedisPool = _

    //获取Jedis连接
    def getJedisClient : Jedis = {
        if (jedisPool == null) {
            println("新建一个线程池！")
            val properties: Properties = PropertiesUtil.load("config.properties")
            val redisHost: String = properties.getProperty("redis.host")
            val redisPort: String = properties.getProperty("redis.port")
            val jedisPoolConfig = new JedisPoolConfig
            jedisPoolConfig.setMaxTotal(100)//最大连接数
            jedisPoolConfig.setMaxIdle(20)//最大空闲
            jedisPoolConfig.setMinIdle(20)//最小空闲
            jedisPoolConfig.setBlockWhenExhausted(true)//忙碌时是否等待
            jedisPoolConfig.setMaxWaitMillis(500)//忙碌时最大等待时长
            jedisPoolConfig.setTestOnBorrow(true)//每次获取连接时是否测试
            jedisPool = new JedisPool(jedisPoolConfig,redisHost,redisPort.toInt)
        }
        jedisPool.getResource
    }

}
