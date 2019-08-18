package com.common.district.ad.service;

import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.vo.ReceiptAmountRequestVo;
import com.common.district.ad.vo.ReceivablesBillDerateReturnVo;
import com.common.district.ad.vo.ReceivablesBillDerateVo;
import com.common.district.ad.vo.ReceivablesBillVo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.LoginUser;

import java.util.List;

public interface ReceivablesBillDerateService {
    /**
     * 根据条件获取应收账单表集合
     * @param vo
     * @return
     */
    List<ReceivablesBillDerateReturnVo> selectReceivablesBillDerateByCondition(ReceivablesBillDerateVo vo);
    /**
     * 根据条件获取应收账单表数量
     * @param vo
     * @return
     */
    Integer selectReceivablesBillDerateCountByCondition(ReceivablesBillDerateVo vo);

    /**
     * 校验满足可以发起应收减免流程的条件
     * @param vo
     * @return
     */
    RespData amountDerateVal(List<ReceiptAmountRequestVo> vo);
    /**
     * 校验满足可以发起违约金流程的条件
     * @param vo
     * @return
     */
    RespData violateDerateVal(List<ReceiptAmountRequestVo> vo);
    /**
     * 保存应收金额
     * @param vo
     * @return
     */
    RespData amountDerateSave(List<ReceiptAmountRequestVo> vo,LoginUser loginUser);
    /**
     * 保存违约金金额
     * @param vo
     * @return
     */
    RespData violateDerateSave(List<ReceiptAmountRequestVo> vo,LoginUser loginUser);
}
