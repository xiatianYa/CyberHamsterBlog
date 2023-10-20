package com.hncpu.blog;

import com.hncpu.blog.utils.Md5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void test(){}
    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("1","2");
        System.out.println(redisTemplate.opsForValue().get("1"));
    }
    @Test
    public void testMd5(){
        String password = Md5Utils.encryptPassword("夏天", "123456", "9556");
        System.out.println(password);
    }
}
