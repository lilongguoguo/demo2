package com.common.district.org.dao;

import com.common.district.org.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单功能信息
 */
@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    //根据租户id获取所有的左侧菜单列表
    List<Menu> getAllMenu(@Param("organId") String organId);

    //根据租户id查询功能
    List<Menu> getMenuFunction(@Param("organId")String organId,@Param("leftMenu") Integer leftMenu,@Param("menuLevel") Integer menuLevel);

    //根据角色id和租户id查询菜单功能
    List<Menu> getMenuFunctionRole(@Param("roleId") String roleId, @Param("organId") String organId ,@Param("menuLevel") Integer menuLevel );

    //根据租户id查询所有菜单功能
    List<Menu> getAll(@Param("organId")String organId);
}