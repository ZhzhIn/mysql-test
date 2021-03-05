package com.mybatistest.dao;
import com.mybatistest.dto.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper {
    public Role getRole(Integer id);
    @Select(value = "select id ,role_name as roleName ,note from role where id = #{id}")
    public Role getRole1(int id);
}