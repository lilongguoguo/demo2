package com.common.district.ad.dao;

import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.vo.ReceivablesBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivablesBillMapper {
    int deleteByPrimaryKey(Long rbId);

    int insert(ReceivablesBill record);

    int insertSelective(ReceivablesBill record);

    ReceivablesBill selectByPrimaryKey(Long rbId);

    int updateByPrimaryKeySelective(ReceivablesBill record);

    int updateByPrimaryKey(ReceivablesBill record);

    /**
     * 根据条件获取应收账单表集合
     * @param vo
     * @return
     */
    List<ReceivablesBill> selectReceivablesBillByCondition(@Param("vo") ReceivablesBillVo vo);
    /**
     * 根据条件获取应收账单表数量
     * @param vo
     * @return
     */
    Integer selectReceivablesBillCountByCondition(@Param("vo") ReceivablesBillVo vo);

    List<ReceivablesBill> selectByPrimaryKeyList(@Param("rbIdList") List<String> rbIdList);


}