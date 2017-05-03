package com.codebase.framework.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Xiaojun.Cheng
 * @date 2017/4/1
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
            System.out.println(blog.toString());
        } finally {
            session.close();
        }
    }

}
