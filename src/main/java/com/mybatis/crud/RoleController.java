package com.mybatis.crud;

import com.mybatis.po.Role;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class RoleController {
    SqlSession sqlSession = null;

    public SqlSessionFactory init() {
        SqlSessionFactory factory = null;
        String resource = "mybatis-config.xml";
        InputStream is;
        System.out.println("mybatis-config.xml");
        try {
            is = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(is);
            System.out.println("build success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factory;
    }
    @Test
    public void select (){
        try {
            // 打开 SqlSession 会话
            sqlSession = init().openSession();
            // some code...
            System.out.println("-----");
            Role role = sqlSession.selectOne("com.mybatis.mapper.RoleMapper.getRole");
            System.out.println("-----");
            System.out.println(role.toString());
            sqlSession.commit();    // 提交事务
        } catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("---catch---");
            sqlSession.rollback();  // 回滚事务
        } finally {
            System.out.println("---finally--");
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
    public <T> T select(String str, Long lon) {
        T t = null;

        try {
            // 打开 SqlSession 会话
            sqlSession = init().openSession();
            // some code...
            System.out.println("str is "+str+" ,lon is "+lon);
            System.out.println("-----");
            System.out.println("sqlsession is "+sqlSession);
            t = sqlSession.selectOne(str, lon);
            System.out.println("-----");
            sqlSession.commit();    // 提交事务
        } catch (Exception e) {
            sqlSession.rollback();  // 回滚事务
        } finally {
            // 在 finally 语句中确保资源被顺利关闭
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return t;
    }
    @Test
    public void testSelect (){
        Role role  = select("com.mybatis.mapper.RoleMapper.getRole",1L);
        System.out.println(role);
    }

}
