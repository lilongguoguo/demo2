package com.common.district.org.dao;

import com.common.district.org.model.Role;
import com.common.district.org.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色信息
 */
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getUserRoleListByUserId(@Param("userId") String userId);

    //查询所有的角色信息
    List<Role> selectAllRole(@Param("vo")RoleVo vo);
    //查询角色统计数
    Integer selectAllRoleCount(@Param("vo")RoleVo vo);

    //根据租户id查询所有角色名
    List<String> getRoleNameList(@Param("originId") String originId);
}