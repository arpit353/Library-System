package com.example.library_system.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

  @Bean
  protected JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//    redisTemplate.setKeySerializer(new StringRedisSerializer());
//    redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
//    redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
//    redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }
}