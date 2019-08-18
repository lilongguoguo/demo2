package com.common.district.org.dao;

import com.common.district.org.model.RoleUser;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserMapper {
    int deleteByPrimaryKey(Long ruId);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser selectByPrimaryKey(Long ruId);

    int updateByPrimaryKeySelective(RoleUser record);

    int updateByPrimaryKey(RoleUser record);
}