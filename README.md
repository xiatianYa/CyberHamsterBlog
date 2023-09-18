

### 赛博仓鼠博客
### 介绍

赛博仓鼠博客(CyberHamsterBlog)，一个基于SpringBoot架构的前后端分离博客系统。Web端使用Vue + Element , 后端使用 Spring boot + mybatis-plus + redis进行开发，使用 So-Token做登录验证和权限校验，文章存储采用阿里云Oss,使用ElasticSearch作为全文检索服务,

### 安装教程
1. npm install | cnpm install(推荐)
2. 用户:npm run serve 管理:npm run dev
3. 管理用户名:admin 123456
4. 数据库在spring-blog 目录中
### 一，项目简介
### 1.技术
(1)，后端 SpringBoot + MybatisPlus + Redis + Mysql + So-Token + elasticsearch
(2)，前端 Vue2 + element-ui + element-admin-template-master
### 1.项目讲解
1. 使用 element-admin-template-master模板开发,开始开发后台管理
2. 项目以单体架构入手，先快速开发，不考虑项目优化，降低开发负担
3. 开发完成后，开始优化项目，提升编程思维能力
4. 比如页面静态化，Redis缓存，Oss云存储，log4j日志等
5. 安全的权限框架，基于so-token安全框架开发
6. docker部署上线
7. 云服务器购买，域名购买，域名备案等
### 二，前端工程搭建

 ```js
npm install 
用户端 npm run server
 后台端 npm run dev
 ```

### 三，后端工程搭建
#### 1.新建maven工程
 ``` java
    <dependencies>
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter</artifactId>
         </dependency>
 
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-test</artifactId>
             <scope>test</scope>
         </dependency>
 
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-web</artifactId>
         </dependency>
 
         <dependency>
             <groupId>com.mysql</groupId>
             <artifactId>mysql-connector-j</artifactId>
             <scope>runtime</scope>
         </dependency>
 
         <dependency>
             <groupId>org.projectlombok</groupId>
             <artifactId>lombok</artifactId>
             <optional>true</optional>
         </dependency>
 
         <!-- druid依赖 -->
         <dependency>
             <groupId>com.alibaba</groupId>
             <artifactId>druid</artifactId>
             <version>1.1.0</version>
         </dependency>
         <!-- mybatisPlus 核心库 -->
         <dependency>
             <groupId>com.baomidou</groupId>
             <artifactId>mybatis-plus-boot-starter</artifactId>
             <version>3.5.2</version>
         </dependency>
         <!-- druid 连接池 -->
         <dependency>
             <groupId>com.alibaba</groupId>
             <artifactId>druid-spring-boot-starter</artifactId>
             <version>1.1.10</version>
         </dependency>
 
         <!-- 阿里云oss依赖 -->
         <dependency>
             <groupId>com.aliyun.oss</groupId>
             <artifactId>aliyun-sdk-oss</artifactId>
             <version>3.1.0</version>
         </dependency>
 
         <!-- 日期工具栏依赖 -->
         <dependency>
             <groupId>joda-time</groupId>
             <artifactId>joda-time</artifactId>
             <version>2.10.1</version>
         </dependency>
         <!-- JWT工具类 -->
         <dependency>
             <groupId>com.auth0</groupId>
             <artifactId>java-jwt</artifactId>
             <version>3.19.1</version>
         </dependency>
         <!-- Redis -->
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-data-redis</artifactId>
         </dependency>
 
         <!-- saToken权限认证框架 -->
         <dependency>
             <groupId>cn.dev33</groupId>
             <artifactId>sa-token-spring-boot-starter</artifactId>
             <version>1.34.0</version>
         </dependency>
 
 
 
         <dependency>
             <groupId>commons-lang</groupId>
             <artifactId>commons-lang</artifactId>
             <version>2.6</version>
         </dependency>
 
     </dependencies>
 
     <build>
         <plugins>
             <plugin>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-maven-plugin</artifactId>
             </plugin>
         </plugins>
     </build>
 ```
#### 2.配置类
#### 2.1 mybatis-plus
 ``` java 
 package com.hncpu.blog.config;
 
 import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
 import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
 import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
 import org.apache.ibatis.reflection.MetaObject;
 import org.springframework.context.annotation.Bean;
 import org.springframework.stereotype.Component;
 import java.util.Date;
 
 @Component
 public class MybatisPlusConfig implements MetaObjectHandler {
     @Override
     public void insertFill(MetaObject metaObject) {
         //设置属性值
         this.setFieldValByName("create_time",new Date(),metaObject);
         this.setFieldValByName("update_time",new Date(),metaObject);
     }
 
     @Override
     public void updateFill(MetaObject metaObject) {
         this.setFieldValByName("updateTime",new Date(),metaObject);
     }
     @Bean
     public MybatisPlusInterceptor paginationInterceptor() {
         MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
 
         //这是分页拦截器
         PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
         paginationInnerInterceptor.setOverflow(true);
         paginationInnerInterceptor.setMaxLimit(500L);
         mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
 
         return mybatisPlusInterceptor;
     }
 }
 ```
#### 2.2 Reids
 ``` java
 package com.hncpu.blog.config;
 
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
 import com.fasterxml.jackson.annotation.PropertyAccessor;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.springframework.cache.CacheManager;
 import org.springframework.cache.annotation.CachingConfigurerSupport;
 import org.springframework.cache.annotation.EnableCaching;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.data.redis.cache.RedisCacheConfiguration;
 import org.springframework.data.redis.cache.RedisCacheManager;
 import org.springframework.data.redis.connection.RedisConnectionFactory;
 import org.springframework.data.redis.core.RedisTemplate;
 import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
 import org.springframework.data.redis.serializer.RedisSerializationContext;
 import org.springframework.data.redis.serializer.RedisSerializer;
 import org.springframework.data.redis.serializer.StringRedisSerializer;
 
 import java.time.Duration;
 
 @EnableCaching
 @Configuration
 public class RedisConfig extends CachingConfigurerSupport {
 
     @Bean
     public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
         RedisTemplate<String, Object> template = new RedisTemplate<>();
         RedisSerializer<String> redisSerializer = new StringRedisSerializer();
         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
         ObjectMapper om = new ObjectMapper();
         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         jackson2JsonRedisSerializer.setObjectMapper(om);
         template.setConnectionFactory(factory);
         //key序列化方式
         template.setKeySerializer(redisSerializer);
         //value序列化
         template.setValueSerializer(jackson2JsonRedisSerializer);
         //value hashmap序列化
         template.setHashValueSerializer(jackson2JsonRedisSerializer);
         return template;
     }
 
     @Bean
     public CacheManager cacheManager(RedisConnectionFactory factory) {
         RedisSerializer<String> redisSerializer = new StringRedisSerializer();
         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
         //解决查询缓存转换异常的问题
         ObjectMapper om = new ObjectMapper();
         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         jackson2JsonRedisSerializer.setObjectMapper(om);
         // 配置序列化（解决乱码的问题）,过期时间600秒
         RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                 .entryTtl(Duration.ofSeconds(600))
                 .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                 .disableCachingNullValues();
         RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                 .cacheDefaults(config)
                 .build();
         return cacheManager;
     }
 }
 ```
#### 2.3 So-Token
 ``` java
 package com.hncpu.blog.config;
 
 import cn.dev33.satoken.interceptor.SaInterceptor;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
 @Configuration
 public class SaTokenConfigure implements WebMvcConfigurer {
     // 注册 Sa-Token 拦截器，打开注解式鉴权功能 
     @Override
     public void addInterceptors(InterceptorRegistry registry) {
         // 注册 Sa-Token 拦截器，打开注解式鉴权功能 
         registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
     }
 }
 ```
#### 2.4 So-Token 权限拦截器
 ``` java
 package com.hncpu.blog.config;
 
 import cn.dev33.satoken.stp.StpInterface;
 import com.hncpu.blog.entity.TbUserEntity;
 import com.hncpu.blog.service.TbUserService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 
 import java.util.ArrayList;
 import java.util.List;
 
 /**
  * 自定义权限验证接口扩展
  */
 @Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
 public class StpInterfaceImpl implements StpInterface {
     @Autowired
     private TbUserService tbUserService;
 
     /**
      * 返回一个账号所拥有的权限码集合 
      */
     @Override
     public List<String> getPermissionList(Object loginId, String loginType) {
         // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
         List<String> list = new ArrayList<String>();
         TbUserEntity TbUser = tbUserService.getById(Integer.parseInt(loginId.toString()));
         int level = TbUser.getLevel();
         switch (level){
             //普通用户 拥有修改 添加 查询
             case 0:
                 list.add("user.get");
                 break;
             //开发人员 拥有修改 添加 查询 删除
             case 1:
                 list.add("user.add");
                 list.add("user.update");
                 list.add("user.get");
                 list.add("user.delete");
                 break;
             //管理人员 拥有修改 添加 查询 删除 用户管理
             case 2:
                 list.add("user.add");
                 list.add("user.update");
                 list.add("user.get");
                 list.add("user.delete");
                 list.add("user.manager");
                 break;
         }
         return list;
     }
 
     /**
      * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
      */
     @Override
     public List<String> getRoleList(Object loginId, String loginType) {
         // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
         List<String> list = new ArrayList<String>();
         list.add("admin");
         list.add("super-admin");
         return list;
     }
 
 }
 ```