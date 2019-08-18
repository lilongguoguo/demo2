package com.common.district.ad.service;

import com.common.district.ad.model.Contract;
import com.common.district.ad.model.ContractCostDicts;
import com.common.district.ad.vo.*;
import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import com.common.district.org.model.User;
import com.common.district.res.vo.CustomerVO;

import java.util.HashMap;
import java.util.List;

public interface ContractService {

    /**
     * 获取合同详情
     * @param contId
     * @param loginUser
     * @return
     */
    RespData<ContractVO> getContract(Long contId, LoginUser loginUser);

    /**
     * 获取框架合同
     * @param organId
     * @return
     */
    RespData<List<Contract>> listCanBeBoundContracts(String organId);

    /**
     * 合同保存
     * @param contractVo
     * @param loginUser
     * @return
     */
    RespData saveContract(ContractVO contractVo, LoginUser loginUser);

    /**
     * 获取我方单位
     * @param ogranId
     * @return
     */
    List<Department> getCompanyList(String ogranId);

    /**
     * 获取我方联系人
     * @param companyId
     * @param userId
     * @return
     */
    RespData<List<User>> getAllDepUserByUserId(String companyId, String userId);

    /**
     * 保证金查询
     * @param contractVo
     * @return
     */
    List<ContractDepositPlanVO> getContractDepositPlanList(ContractVO contractVo);

    /**
     *查询能源信息
     * @param contractVo
     * @return
     */
    List<ContractEnergyPlanVO> getEnergyPlanList(ContractVO contractVo);

    /**
     *收款计划查询
     * @param contractVo
     * @return
     */
    List<ContractIncomeSetVO> getContractIncomeSetList(ContractVO contractVo);

    /**
     * 获取费项
     * @param organId
     * @return
     */
    RespData<ContractCostDictsListVO> getCostItemList(String organId);

}
