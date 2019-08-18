package com.common.district.ad.dao;

import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.model.ReceivablesBillDerate;
import com.common.district.ad.vo.ReceivablesBillDerateReturnVo;
import com.common.district.ad.vo.ReceivablesBillDerateVo;
import com.common.district.ad.vo.ReceivablesBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivablesBillDerateMapper {
    int deleteByPrimaryKey(Long rbdId);

    int insert(ReceivablesBillDerate record);

    int insertSelective(ReceivablesBillDerate record);

    int insertSelectiveKey(ReceivablesBillDerate record);

    ReceivablesBillDerate selectByPrimaryKey(Long rbdId);

    int updateByPrimaryKeySelective(ReceivablesBillDerate record);

    int updateByPrimaryKey(ReceivablesBillDerate record);

    /**
     * 根据条件获取减免数据集合
     * @param vo
     * @return
     */
    List<ReceivablesBillDerateReturnVo> selectReceivablesBillDerateByCondition(@Param("vo") ReceivablesBillDerateVo vo);
    /**
     * 根据条件获取减免数据数量
     * @param vo
     * @return
     */
    Integer selectReceivablesBillDerateCountByCondition(@Param("vo") ReceivablesBillDerateVo vo);

    /**
     * 判断某一账单下是否存在应收减免正在走的流程
     */
    Integer isExistFlowByBillId(Long billId);
    /**
     * 判断某一账单下是否存在违约金减免正在走的流程
     */
    Integer isExistViolateFlowByBillId(Long billId);
}