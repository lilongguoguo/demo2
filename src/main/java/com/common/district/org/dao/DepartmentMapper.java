package com.common.district.org.dao;

import com.common.district.org.model.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String deptId);

    List<Department> getUserDeptListByUserId(@Param("userId") String userId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    //查询启用状态的所有部门
    List<Department> getAllDepartment();
    //根据部门id逻辑删除组织框架(参数：部门id和删除人id)
    int logicDeleteByPrimaryKey(@Param("deptId")String deptId,@Param("delUserId")String delUserId );
    //根据parent_id来查看是否有数据（判断是否逻辑删除）
    int selectParentId(@Param("parentId")String parentId);


    //修改部门信息
    int updateByDepartment(Department department);

    /**
     * 通过租户id获取公司列表
     * @param organId
     * @return
     */
    List<Department> getcompanyListByogranId(@Param("organId") String organId);

    //根据租户id查询所有部门名
    List<String> getDeptNameList(@Param("originId")String originId);
}