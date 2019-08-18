package com.common.district.ad.dao;

import com.common.district.ad.model.ContractAttach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractAttachMapper {
    int deleteByPrimaryKey(Long contAttachId);

    int insert(ContractAttach record);

    int insertSelective(ContractAttach record);

    ContractAttach selectByPrimaryKey(Long contAttachId);

    int updateByPrimaryKeySelective(ContractAttach record);

    int updateByPrimaryKey(ContractAttach record);

    /**
     * 批量插入数据
     * @param lists
     * @return
     */
    int insertContractAttach(@Param("lists")List<ContractAttach> lists);

    /**
     * 根据附件ids删除附件
     * @param contAttachIds
     * @return: int
     */
    int deleteContractAttachByBatch(@Param("contAttachIds") List<Long> contAttachIds);

    /**
     * 根据合同ID获取合同附件
     * @param contId
     * @return: List<ContractAttach>
     */
    List<ContractAttach> getAttachListByContId(Long contId);
}