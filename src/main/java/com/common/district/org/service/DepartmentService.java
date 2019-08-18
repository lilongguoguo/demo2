package com.common.district.org.service;

import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;

/**
 * 组织框架
 */
public interface DepartmentService {
    /**
     * 查询组织框架（查询所有部门，到顶级部门）
     * @return
     */
    RespData getAllDepartment();

    /**
     * 添加组织框架（添加部门）
     * @return
     */
    RespData saveDepartment(Department department);

    /**
     * 删除组织框架（逻辑删除部门并且停用）
     * @return
     */
    RespData deleteDepartment(Department department);

    /**
     * 查询全部部门类型
     * @return
     */
    RespData getDepartmentOfType(String organId);

    /**
     * 修改部门信息
     * @param department
     * @return
     */
    RespData revisionDepartment(Department department);
}
