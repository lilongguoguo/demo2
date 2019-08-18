package com.common.district.ad.dao;

import com.common.district.ad.model.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractMapper {
    int deleteByPrimaryKey(Long contId);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(Long contId);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKeyWithBLOBs(Contract record);

    int updateByPrimaryKey(Contract record);

    /**
     * query can be bound by the contract list
     * @param organId
     * @param contStatus
     * @return: List<Contract>
     */
    List<Contract> listCanBeBoundContracts(@Param("contStatus") Integer contStatus, @Param("organId") String organId);
}