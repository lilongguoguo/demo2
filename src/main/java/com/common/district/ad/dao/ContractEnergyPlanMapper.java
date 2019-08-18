package com.common.district.ad.dao;

import com.common.district.ad.model.ContractEnergyPlan;
import com.common.district.ad.vo.ContractVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractEnergyPlanMapper {
    int deleteByPrimaryKey(Long energyPlanId);

    int insert(ContractEnergyPlan record);

    int insertSelective(ContractEnergyPlan record);

    ContractEnergyPlan selectByPrimaryKey(Long energyPlanId);

    int updateByPrimaryKeySelective(ContractEnergyPlan record);

    int updateByPrimaryKey(ContractEnergyPlan record);

    /**
     * 批量插入数据
     * @param record
     * @return
     */
    int insertEnergyPlans(@Param("lists") List<ContractEnergyPlan> record);
    /**
     * 根据ids删除能源信息
     * @param energyPlans
     * @return
     */
    int deleteEnergyPlanId(@Param("planIds")List<Long> energyPlans);

    /**
     * 根据合同ID获取能源信息
     * @param contractVo
     * @return
     */
    List<ContractEnergyPlan> getEnergyPlanList(@Param("search")ContractVO contractVo);
}