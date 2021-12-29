package com.mybatistest.crud;

import com.mybatistest.dao.RoleMapper;
import com.mybatistest.dto.Role;
import com.mybatistest.utils.SqlSessionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

@Slf4j
public class RoleController {
    SqlSession sqlSession = null;
//    SqlSession sqlSession = null;
//    SqlSession sqlSession = null;
//    hotfix
//    hotfix
    public Role selectRole(int id) {
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role /*role = roleMapper.getRole(id);*/
            role = roleMapper.getRole1(id);
            log.info("role_name=>" + role.getRoleName());
            return role;
        } catch (Exception ex) {
            log.error(ex.getMessage());

            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return null;
    }
    @Test
    public void testSelect() {
        Role role = selectRole(1);
        System.out.println(role.getRoleName());
    }

}
