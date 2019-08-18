package com.common.district.res.dao;

import com.common.district.res.model.Bank;

public interface BankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);
}