package com.common.district.org.dao;

import com.common.district.org.model.MenuRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRoleMapper {
    int deleteByPrimaryKey(Long mrId);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Long mrId);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    //添加角色菜单中间表
    int insertList(@Param("list") List<MenuRole> menuRoleList);

    //根据角色id查询菜单功能表的主键menu_id
    List<Long> selectByRoleId(@Param("roleId") String roleId,@Param("organId") String organId);

    //根据角色id查询数据范围
    String selectByDataRange(String roleId);

    //根据角色id物理删除角色菜单功能中间表信息
    Integer deleteByRoleId(String roleId);

    //根据角色id修改角色菜单中间表的状态
    Integer updateStatus(@Param("roleId") String roleId,@Param("status") Integer status);
}