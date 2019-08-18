package com.common.district.ad.service;

import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.vo.ReceivablesBillVo;
import com.common.district.res.model.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceivablesBillService {
    /**
     * 根据条件获取应收账单表集合
     * @param vo
     * @return
     */
    List<ReceivablesBill> selectReceivablesBillByCondition(ReceivablesBillVo vo);
    /**
     * 根据条件获取应收账单表数量
     * @param vo
     * @return
     */
    Integer selectReceivablesBillCountByCondition(ReceivablesBillVo vo);
    /**
     * 根据billId获取应收账单详情
     * @param
     * @return
     */
    ReceivablesBill selectReceivablesBillDetailByCondition(Long billId);

    /**
     * 根据条件获取应收账单表集合
     * @param rbIdList
     * @return
     */
    List<ReceivablesBill> selectReceivablesBillByRbIdList(List<String> rbIdList);


}
