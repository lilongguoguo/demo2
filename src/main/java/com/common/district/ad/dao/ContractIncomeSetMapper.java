package com.common.district.ad.dao;

import com.common.district.ad.model.ContractIncomeSet;
import com.common.district.ad.vo.ContractVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractIncomeSetMapper {
    int deleteByPrimaryKey(Long incomePlanId);

    int insert(ContractIncomeSet record);

    int insertSelective(ContractIncomeSet record);

    ContractIncomeSet selectByPrimaryKey(Long incomePlanId);

    int updateByPrimaryKeySelective(ContractIncomeSet record);

    int updateByPrimaryKey(ContractIncomeSet record);

    /**
     * 批量插入
     * @param lists
     * @return
     */
    int insertIncomeSets(@Param("lists")List<ContractIncomeSet> lists);

    /**
     * 删除收款计划
     * @param ids
     * @return
     */
    int deleteIncomeSet(@Param("contId")Long contId, @Param("ids")List<Long> ids);

    /**
     * 根据合同ID删除收款计划
     * @param contId
     * @return
     */
    int deleteIncomeSetByContDetailIds(@Param("contId")Long contId);

    /**
     * 根据合同ID获取收款计划
     * @param contractVo
     * @return
     */
    List<ContractIncomeSet> getContractIncomeSetList(@Param("search")ContractVO contractVo);
}