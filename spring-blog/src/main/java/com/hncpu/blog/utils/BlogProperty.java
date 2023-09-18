package com.hncpu.blog.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogProperty implements InitializingBean {
    @Value("${blog.Md5Salt}")
    private static String Md5Salt;
    private static String Avatar="https://edu-9556.oss-cn-hangzhou.aliyuncs.com/2023/09/11/464efe07-d917-48fc-80cd-bac90b7a7940.jpg";

    public static String MD5_SALT;
    public static String AVATAR;

    @Override
    public void afterPropertiesSet() throws Exception {
        MD5_SALT=Md5Salt;
        AVATAR=Avatar;
    }
}
