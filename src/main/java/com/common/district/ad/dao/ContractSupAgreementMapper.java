package com.common.district.ad.dao;

import com.common.district.ad.model.ContractSupAgreement;

public interface ContractSupAgreementMapper {
    int deleteByPrimaryKey(Long supId);

    int insert(ContractSupAgreement record);

    int insertSelective(ContractSupAgreement record);

    ContractSupAgreement selectByPrimaryKey(Long supId);

    int updateByPrimaryKeySelective(ContractSupAgreement record);

    int updateByPrimaryKeyWithBLOBs(ContractSupAgreement record);

    int updateByPrimaryKey(ContractSupAgreement record);
}