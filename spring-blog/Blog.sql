CREATE DATABASE  IF NOT EXISTS `blog_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `blog_db`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: blog_db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_blog`
--

DROP TABLE IF EXISTS `tb_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog` (
  `blog_id` int NOT NULL AUTO_INCREMENT COMMENT '文章表主键id',
  `blog_title` varchar(200) NOT NULL COMMENT '文章标题',
  `blog_content` mediumtext NOT NULL COMMENT '文章内容',
  `blog_classify_id` int NOT NULL COMMENT '文章归档id',
  `blog_status` tinyint NOT NULL DEFAULT '0' COMMENT '0-草稿 1-发布',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`blog_id`),
  KEY `blog_classify_id` (`blog_classify_id`),
  CONSTRAINT `tb_blog_ibfk_1` FOREIGN KEY (`blog_classify_id`) REFERENCES `tb_classify` (`classify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog`
--

LOCK TABLES `tb_blog` WRITE;
/*!40000 ALTER TABLE `tb_blog` DISABLE KEYS */;
INSERT INTO `tb_blog` VALUES (1,'我的第一个博客','## 我的Blog项目\r ### 一，项目简介\r ### 1.技术\r (1)，后端 SpringBoot + MybatisPlus + Redis + Mysql + So-Token + elasticsearch\r (2)，前端 Vue2 + element-ui + element-admin-template-master\r ### 1.项目讲解\r 1. 使用 element-admin-template-master模板开发,开始开发后台管理\r 2. 项目以单体架构入手，先快速开发，不考虑项目优化，降低开发负担\r 3. 开发完成后，开始优化项目，提升编程思维能力\r 4. 比如页面静态化，Redis缓存，Oss云存储，log4j日志等\r 5. 安全的权限框架，基于so-token安全框架开发\r 6. docker部署上线\r 7. 云服务器购买，域名购买，域名备案等\r ### 二，前端工程搭建\r ```js\r npm install \r 用户端 npm run server\r 后台端 npm run dev\r ```\r ### 三，后端工程搭建\r #### 1.新建maven工程\r ``` java\r    <dependencies>\r         <dependency>\r             <groupId>org.springframework.boot</groupId>\r             <artifactId>spring-boot-starter</artifactId>\r         </dependency>\r \r         <dependency>\r             <groupId>org.springframework.boot</groupId>\r             <artifactId>spring-boot-starter-test</artifactId>\r             <scope>test</scope>\r         </dependency>\r \r         <dependency>\r             <groupId>org.springframework.boot</groupId>\r             <artifactId>spring-boot-starter-web</artifactId>\r         </dependency>\r \r         <dependency>\r             <groupId>com.mysql</groupId>\r             <artifactId>mysql-connector-j</artifactId>\r             <scope>runtime</scope>\r         </dependency>\r \r         <dependency>\r             <groupId>org.projectlombok</groupId>\r             <artifactId>lombok</artifactId>\r             <optional>true</optional>\r         </dependency>\r \r         <!-- druid依赖 -->\r         <dependency>\r             <groupId>com.alibaba</groupId>\r             <artifactId>druid</artifactId>\r             <version>1.1.0</version>\r         </dependency>\r         <!-- mybatisPlus 核心库 -->\r         <dependency>\r             <groupId>com.baomidou</groupId>\r             <artifactId>mybatis-plus-boot-starter</artifactId>\r             <version>3.5.2</version>\r         </dependency>\r         <!-- druid 连接池 -->\r         <dependency>\r             <groupId>com.alibaba</groupId>\r             <artifactId>druid-spring-boot-starter</artifactId>\r             <version>1.1.10</version>\r         </dependency>\r \r         <!-- 阿里云oss依赖 -->\r         <dependency>\r             <groupId>com.aliyun.oss</groupId>\r             <artifactId>aliyun-sdk-oss</artifactId>\r             <version>3.1.0</version>\r         </dependency>\r \r         <!-- 日期工具栏依赖 -->\r         <dependency>\r             <groupId>joda-time</groupId>\r             <artifactId>joda-time</artifactId>\r             <version>2.10.1</version>\r         </dependency>\r         <!-- JWT工具类 -->\r         <dependency>\r             <groupId>com.auth0</groupId>\r             <artifactId>java-jwt</artifactId>\r             <version>3.19.1</version>\r         </dependency>\r         <!-- Redis -->\r         <dependency>\r             <groupId>org.springframework.boot</groupId>\r             <artifactId>spring-boot-starter-data-redis</artifactId>\r         </dependency>\r \r         <!-- saToken权限认证框架 -->\r         <dependency>\r             <groupId>cn.dev33</groupId>\r             <artifactId>sa-token-spring-boot-starter</artifactId>\r             <version>1.34.0</version>\r         </dependency>\r \r \r \r         <dependency>\r             <groupId>commons-lang</groupId>\r             <artifactId>commons-lang</artifactId>\r             <version>2.6</version>\r         </dependency>\r \r     </dependencies>\r \r     <build>\r         <plugins>\r             <plugin>\r                 <groupId>org.springframework.boot</groupId>\r                 <artifactId>spring-boot-maven-plugin</artifactId>\r             </plugin>\r         </plugins>\r     </build>\r ```\r #### 2.配置类\r #### 2.1 mybatis-plus\r ``` java \r package com.hncpu.blog.config;\r \r import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;\r import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;\r import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;\r import org.apache.ibatis.reflection.MetaObject;\r import org.springframework.context.annotation.Bean;\r import org.springframework.stereotype.Component;\r import java.util.Date;\r \r @Component\r public class MybatisPlusConfig implements MetaObjectHandler {\r     @Override\r     public void insertFill(MetaObject metaObject) {\r         //设置属性值\r         this.setFieldValByName(\"create_time\",new Date(),metaObject);\r         this.setFieldValByName(\"update_time\",new Date(),metaObject);\r     }\r \r     @Override\r     public void updateFill(MetaObject metaObject) {\r         this.setFieldValByName(\"updateTime\",new Date(),metaObject);\r     }\r     @Bean\r     public MybatisPlusInterceptor paginationInterceptor() {\r         MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();\r \r         //这是分页拦截器\r         PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();\r         paginationInnerInterceptor.setOverflow(true);\r         paginationInnerInterceptor.setMaxLimit(500L);\r         mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);\r \r         return mybatisPlusInterceptor;\r     }\r }\r ```\r #### 2.2 Reids\r ``` java\r package com.hncpu.blog.config;\r \r import com.fasterxml.jackson.annotation.JsonAutoDetect;\r import com.fasterxml.jackson.annotation.PropertyAccessor;\r import com.fasterxml.jackson.databind.ObjectMapper;\r import org.springframework.cache.CacheManager;\r import org.springframework.cache.annotation.CachingConfigurerSupport;\r import org.springframework.cache.annotation.EnableCaching;\r import org.springframework.context.annotation.Bean;\r import org.springframework.context.annotation.Configuration;\r import org.springframework.data.redis.cache.RedisCacheConfiguration;\r import org.springframework.data.redis.cache.RedisCacheManager;\r import org.springframework.data.redis.connection.RedisConnectionFactory;\r import org.springframework.data.redis.core.RedisTemplate;\r import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;\r import org.springframework.data.redis.serializer.RedisSerializationContext;\r import org.springframework.data.redis.serializer.RedisSerializer;\r import org.springframework.data.redis.serializer.StringRedisSerializer;\r \r import java.time.Duration;\r \r @EnableCaching\r @Configuration\r public class RedisConfig extends CachingConfigurerSupport {\r \r     @Bean\r     public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {\r         RedisTemplate<String, Object> template = new RedisTemplate<>();\r         RedisSerializer<String> redisSerializer = new StringRedisSerializer();\r         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);\r         ObjectMapper om = new ObjectMapper();\r         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);\r         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);\r         jackson2JsonRedisSerializer.setObjectMapper(om);\r         template.setConnectionFactory(factory);\r         //key序列化方式\r         template.setKeySerializer(redisSerializer);\r         //value序列化\r         template.setValueSerializer(jackson2JsonRedisSerializer);\r         //value hashmap序列化\r         template.setHashValueSerializer(jackson2JsonRedisSerializer);\r         return template;\r     }\r \r     @Bean\r     public CacheManager cacheManager(RedisConnectionFactory factory) {\r         RedisSerializer<String> redisSerializer = new StringRedisSerializer();\r         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);\r         //解决查询缓存转换异常的问题\r         ObjectMapper om = new ObjectMapper();\r         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);\r         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);\r         jackson2JsonRedisSerializer.setObjectMapper(om);\r         // 配置序列化（解决乱码的问题）,过期时间600秒\r         RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()\r                 .entryTtl(Duration.ofSeconds(600))\r                 .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))\r                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))\r                 .disableCachingNullValues();\r         RedisCacheManager cacheManager = RedisCacheManager.builder(factory)\r                 .cacheDefaults(config)\r                 .build();\r         return cacheManager;\r     }\r }\r ```\r #### 2.3 So-Token\r ``` java\r package com.hncpu.blog.config;\r \r import cn.dev33.satoken.interceptor.SaInterceptor;\r import org.springframework.context.annotation.Configuration;\r import org.springframework.web.servlet.config.annotation.InterceptorRegistry;\r import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;\r \r @Configuration\r public class SaTokenConfigure implements WebMvcConfigurer {\r     // 注册 Sa-Token 拦截器，打开注解式鉴权功能 \r     @Override\r     public void addInterceptors(InterceptorRegistry registry) {\r         // 注册 Sa-Token 拦截器，打开注解式鉴权功能 \r         registry.addInterceptor(new SaInterceptor()).addPathPatterns(\"/**\");\r     }\r }\r ```\r #### 2.4 So-Token 权限拦截器\r ``` java\r package com.hncpu.blog.config;\r \r import cn.dev33.satoken.stp.StpInterface;\r import com.hncpu.blog.entity.TbUserEntity;\r import com.hncpu.blog.service.TbUserService;\r import org.springframework.beans.factory.annotation.Autowired;\r import org.springframework.stereotype.Component;\r \r import java.util.ArrayList;\r import java.util.List;\r \r /**\r  * 自定义权限验证接口扩展\r  */\r @Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展\r public class StpInterfaceImpl implements StpInterface {\r     @Autowired\r     private TbUserService tbUserService;\r \r     /**\r      * 返回一个账号所拥有的权限码集合 \r      */\r     @Override\r     public List<String> getPermissionList(Object loginId, String loginType) {\r         // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限\r         List<String> list = new ArrayList<String>();\r         TbUserEntity TbUser = tbUserService.getById(Integer.parseInt(loginId.toString()));\r         int level = TbUser.getLevel();\r         switch (level){\r             //普通用户 拥有修改 添加 查询\r             case 0:\r                 list.add(\"user.get\");\r                 break;\r             //开发人员 拥有修改 添加 查询 删除\r             case 1:\r                 list.add(\"user.add\");\r                 list.add(\"user.update\");\r                 list.add(\"user.get\");\r                 list.add(\"user.delete\");\r                 break;\r             //管理人员 拥有修改 添加 查询 删除 用户管理\r             case 2:\r                 list.add(\"user.add\");\r                 list.add(\"user.update\");\r                 list.add(\"user.get\");\r                 list.add(\"user.delete\");\r                 list.add(\"user.manager\");\r                 break;\r         }\r         return list;\r     }\r \r     /**\r      * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)\r      */\r     @Override\r     public List<String> getRoleList(Object loginId, String loginType) {\r         // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色\r         List<String> list = new ArrayList<String>();\r         list.add(\"admin\");\r         list.add(\"super-admin\");\r         return list;\r     }\r \r }\r ```\r ',3,0,0,'2023-09-15 13:11:06','2023-09-16 15:51:17');
/*!40000 ALTER TABLE `tb_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog_count`
--

DROP TABLE IF EXISTS `tb_blog_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_count` (
  `count_id` int NOT NULL AUTO_INCREMENT COMMENT '统计表主键',
  `blog_id` int NOT NULL COMMENT '文章id',
  `count_number` int NOT NULL COMMENT '文章访问数量',
  PRIMARY KEY (`count_id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `tb_blog_count_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `tb_blog` (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_count`
--

LOCK TABLES `tb_blog_count` WRITE;
/*!40000 ALTER TABLE `tb_blog_count` DISABLE KEYS */;
INSERT INTO `tb_blog_count` VALUES (1,1,10);
/*!40000 ALTER TABLE `tb_blog_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_blog_tags_relation`
--

DROP TABLE IF EXISTS `tb_blog_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_blog_tags_relation` (
  `classify_tags_relation_id` int NOT NULL AUTO_INCREMENT COMMENT '中间表主键',
  `blog_id` int NOT NULL COMMENT '文章表的id',
  `tags_id` int NOT NULL DEFAULT '1' COMMENT '标签表id',
  PRIMARY KEY (`classify_tags_relation_id`),
  KEY `blog_id` (`blog_id`),
  KEY `tags_id` (`tags_id`),
  CONSTRAINT `tb_blog_tags_relation_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `tb_blog` (`blog_id`),
  CONSTRAINT `tb_blog_tags_relation_ibfk_2` FOREIGN KEY (`tags_id`) REFERENCES `tb_tags` (`tags_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_blog_tags_relation`
--

LOCK TABLES `tb_blog_tags_relation` WRITE;
/*!40000 ALTER TABLE `tb_blog_tags_relation` DISABLE KEYS */;
INSERT INTO `tb_blog_tags_relation` VALUES (108,1,16);
/*!40000 ALTER TABLE `tb_blog_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_classify`
--

DROP TABLE IF EXISTS `tb_classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_classify` (
  `classify_id` int NOT NULL AUTO_INCREMENT COMMENT '归档表主键',
  `classify_name` varchar(50) NOT NULL COMMENT '归档的名称',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`classify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_classify`
--

LOCK TABLES `tb_classify` WRITE;
/*!40000 ALTER TABLE `tb_classify` DISABLE KEYS */;
INSERT INTO `tb_classify` VALUES (1,'前端',0,'2023-09-15 12:45:01','2023-09-15 12:45:01'),(2,'后端',0,'2023-09-15 12:45:01','2023-09-15 12:45:01'),(3,'日常生活',0,'2023-09-15 12:45:01','2023-09-15 12:45:01');
/*!40000 ALTER TABLE `tb_classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_classify_count`
--

DROP TABLE IF EXISTS `tb_classify_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_classify_count` (
  `count_id` int NOT NULL AUTO_INCREMENT COMMENT '统计表主键',
  `classIfy_id` int NOT NULL COMMENT '归档id',
  `create_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`count_id`),
  KEY `classIfy_id` (`classIfy_id`),
  CONSTRAINT `tb_classify_count_ibfk_1` FOREIGN KEY (`classIfy_id`) REFERENCES `tb_classify` (`classify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_classify_count`
--

LOCK TABLES `tb_classify_count` WRITE;
/*!40000 ALTER TABLE `tb_classify_count` DISABLE KEYS */;
INSERT INTO `tb_classify_count` VALUES (1,3,'2023-09-16 13:37:50');
/*!40000 ALTER TABLE `tb_classify_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_friend`
--

DROP TABLE IF EXISTS `tb_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_friend` (
  `friend_id` int NOT NULL AUTO_INCREMENT COMMENT '友链表主键',
  `friend_name` varchar(20) NOT NULL COMMENT '友链的姓名',
  `friend_address` varchar(30) NOT NULL COMMENT '友链的网址',
  `friend_image` varchar(100) NOT NULL COMMENT '友链的图片',
  `friend_signature` varchar(30) NOT NULL COMMENT '个性签名',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_friend`
--

LOCK TABLES `tb_friend` WRITE;
/*!40000 ALTER TABLE `tb_friend` DISABLE KEYS */;
INSERT INTO `tb_friend` VALUES (1,'夏天','www.inadvertently.top','https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg','我亦无他,唯手熟尔',0,'2023-09-16 15:51:17');
/*!40000 ALTER TABLE `tb_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_message`
--

DROP TABLE IF EXISTS `tb_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_message` (
  `message_id` int NOT NULL AUTO_INCREMENT COMMENT '留言主键',
  `message_name` varchar(20) NOT NULL COMMENT '名称',
  `message_avatar` varchar(100) NOT NULL COMMENT '头像',
  `message_type` int NOT NULL COMMENT '评论类型',
  `message_color` varchar(50) NOT NULL COMMENT '评论颜色',
  `message_content` char(100) NOT NULL COMMENT '内容',
  `message_like` int DEFAULT NULL COMMENT '点赞',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_message`
--

LOCK TABLES `tb_message` WRITE;
/*!40000 ALTER TABLE `tb_message` DISABLE KEYS */;
INSERT INTO `tb_message` VALUES (46,'dk','https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg',0,'background-color: rgb(252, 175, 162);','你好,陌生人',11,'2023-09-16 15:52:17'),(47,'pei','https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg',7,'background-color: rgb(168, 237, 138);','hello,',6,'2023-09-16 15:52:37');
/*!40000 ALTER TABLE `tb_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_message_report`
--

DROP TABLE IF EXISTS `tb_message_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_message_report` (
  `report_id` int NOT NULL AUTO_INCREMENT COMMENT '举报表主键',
  `message_id` int NOT NULL COMMENT '评论表主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`report_id`),
  KEY `message_id` (`message_id`),
  CONSTRAINT `tb_message_report_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `tb_message` (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_message_report`
--

LOCK TABLES `tb_message_report` WRITE;
/*!40000 ALTER TABLE `tb_message_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_message_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_reply`
--

DROP TABLE IF EXISTS `tb_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_reply` (
  `reply_id` int NOT NULL AUTO_INCREMENT COMMENT '回复表主键',
  `message_id` int NOT NULL COMMENT '留言表id',
  `reply_name` varchar(20) NOT NULL COMMENT '名称',
  `reply_avatar` varchar(100) NOT NULL COMMENT '头像',
  `reply_content` char(100) NOT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`reply_id`),
  KEY `message_id` (`message_id`),
  CONSTRAINT `tb_reply_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `tb_message` (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_reply`
--

LOCK TABLES `tb_reply` WRITE;
/*!40000 ALTER TABLE `tb_reply` DISABLE KEYS */;
INSERT INTO `tb_reply` VALUES (22,46,'夏天','https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg','你好','2023-09-16 19:50:09');
/*!40000 ALTER TABLE `tb_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tags`
--

DROP TABLE IF EXISTS `tb_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tags` (
  `tags_id` int NOT NULL AUTO_INCREMENT COMMENT '标签表主键',
  `tags_name` varchar(50) NOT NULL COMMENT '标签的名称',
  `tags_rank` int NOT NULL DEFAULT '1' COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tags_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tags`
--

LOCK TABLES `tb_tags` WRITE;
/*!40000 ALTER TABLE `tb_tags` DISABLE KEYS */;
INSERT INTO `tb_tags` VALUES (16,'日常生活',1,0,'2023-09-16 15:51:03'),(17,'SpringBoot',1,0,'2023-09-16 15:56:20'),(18,'SpringCloud',1,0,'2023-09-16 15:56:27'),(19,'So-Token',1,0,'2023-09-16 15:56:39'),(20,'Html 5/Css 3',1,0,'2023-09-16 15:56:48'),(21,'Ajax',1,0,'2023-09-16 15:56:53'),(22,'Vue',1,0,'2023-09-16 15:56:56'),(23,'Docker',1,0,'2023-09-16 15:57:01'),(24,'Java',1,0,'2023-09-16 15:57:06'),(25,'Python',1,0,'2023-09-16 15:57:11'),(26,'SSM',1,0,'2023-09-16 15:57:16'),(27,'Java Web',1,0,'2023-09-16 15:57:21'),(28,'JavaScript',1,0,'2023-09-16 15:57:39'),(29,'React',1,0,'2023-09-16 15:57:55'),(30,'Mybatis',1,0,'2023-09-16 15:58:07'),(31,'Mybatis-Plus',1,0,'2023-09-16 15:58:17'),(32,'英语单词',1,0,'2023-09-16 15:58:37'),(33,'Mysql',1,0,'2023-09-16 15:58:46'),(34,'数学',1,0,'2023-09-16 15:58:57');
/*!40000 ALTER TABLE `tb_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `admin_user_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `avatar` varchar(100) NOT NULL COMMENT '用户头像',
  `login_user_name` varchar(10) NOT NULL COMMENT '管理员登陆账号',
  `login_password` varchar(50) NOT NULL COMMENT '管理员登陆密码',
  `level` int NOT NULL COMMENT '权限级别',
  `locked` tinyint DEFAULT '0' COMMENT '是否锁定',
  PRIMARY KEY (`admin_user_id`),
  UNIQUE KEY `login_user_name` (`login_user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1832951818 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1832951815,'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg','tourist','c5aeb568cdf204a1818db7026f37efba',0,0),(1832951816,'https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg','admin','bb3dcc3b8a5c6537e48460476e3472e0',2,0);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_userinfo`
--

DROP TABLE IF EXISTS `tb_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_userinfo` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户信息表主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `avatar` varchar(100) NOT NULL COMMENT '头像',
  `introduce` mediumtext NOT NULL COMMENT '个人介绍',
  `github` varchar(50) DEFAULT NULL COMMENT 'github地址',
  `gitee` varchar(50) DEFAULT NULL COMMENT 'github地址',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userinfo`
--

LOCK TABLES `tb_userinfo` WRITE;
/*!40000 ALTER TABLE `tb_userinfo` DISABLE KEYS */;
INSERT INTO `tb_userinfo` VALUES (1,'夏天','https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg','# 关于我\r 大三学生,一个死肥宅\r 学习的方向是Java后端工程师\r \r # 掌握的技术栈和项目经历\r ## 技术栈\r 前端: Html5+Css3 Javascript+Es6-Es11 Vue.js\r 后端:Java Mysql JavaWeb SSM Mybatis SpringBoot SpringCloud Mybatis-Plus ES Dobbo\r # 其他学习语言\r C++ Python\r # 个人爱好\r - 跑步\r - 看番\r - 写代码\r # 后记\r 博客使用 Vue2 + SpringBoot\r ','https://github.com/xiatianYa','https://gitee.com/xiatianYa1003','939648675@qq.com','18074589556');
/*!40000 ALTER TABLE `tb_userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-18 10:09:45
