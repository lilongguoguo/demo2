package com.common.district.org.dao;

import com.common.district.org.model.DepartmentUser;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepartmentUser record);

    int insertSelective(DepartmentUser record);

    DepartmentUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartmentUser record);

    int updateByPrimaryKey(DepartmentUser record);
}