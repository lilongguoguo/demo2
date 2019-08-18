package com.common.district.ad.dao;

import com.common.district.ad.model.ContractDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractDetailMapper {
    int deleteByPrimaryKey(Long contDetailId);

    int insert(ContractDetail record);

    int insertSelective(ContractDetail record);

    ContractDetail selectByPrimaryKey(Long contDetailId);

    int updateByPrimaryKeySelective(ContractDetail record);

    int updateByPrimaryKey(ContractDetail record);

    /**
     * @Description：根据合同ID逻辑删除投放明细记录
     * @Patams：[contId]
     */
    int delContractDetailByConId(Long contId);

    /**
     * 根据ids删除投放明细，并更新投放明细的状态
     * @param contDetailIds
     * @return: int
     */
    int deleteContractDetailByContDetailIds(@Param("contDetailIds") List<Long> contDetailIds);

    /**
     * 根据合同id查询所有投放明细
     *
     * @param contId
     * @return: List<ContractDetail>
     */
    List<ContractDetail> getContractDetailByContId(@Param("contId")Long contId, @Param("organId") String organId);
	
	/**
     * 查询已出租和未出租点位数量
     */
    int getPositionCount(@Param("projectId")String projectId,@Param("status")Integer status);
}