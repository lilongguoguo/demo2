package com.common.district.ad.dao;

import com.common.district.ad.model.ContractDepositPlan;
import com.common.district.ad.vo.ContractVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractDepositPlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContractDepositPlan record);

    int insertSelective(ContractDepositPlan record);

    ContractDepositPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContractDepositPlan record);

    int updateByPrimaryKey(ContractDepositPlan record);

    /**
     * 批量插入
     * @param lists
     * @return
     */
    int insertDepositPlans(@Param("lists")List<ContractDepositPlan> lists);

    /**
     * 删除保证金
     * @param energyPlans
     * @return
     */
    int deleteDepositPlan(@Param("contId")Long contId,@Param("planIds")List<Long> energyPlans);

    /**
     * 根据合同ID获取保证金
     * @param contractVo
     * @return
     */
    List<ContractDepositPlan> getContractDepositPlanList(@Param("search") ContractVO contractVo);

    /**
     * 根据合同ID删除保证金
     * @param contId
     * @return
     */
    int deleteDepositPlanByContDetailIds(Long contId);
}