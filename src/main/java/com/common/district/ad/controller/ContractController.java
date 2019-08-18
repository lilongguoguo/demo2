package com.common.district.ad.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.district.ad.model.Contract;
import com.common.district.ad.model.ContractCostDicts;
import com.common.district.ad.service.ContractService;
import com.common.district.ad.vo.*;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import com.common.district.org.model.User;
import com.common.district.res.model.Company;
import com.common.district.res.vo.CustomerVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/contract")
public class ContractController extends BaseController{

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = {"detail"}, method = {RequestMethod.POST})
    @ApiOperation(notes = "获取合同数据", httpMethod = "POST", value = "获取合同数据", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<ContractVO> getContract(HttpServletRequest request, @RequestBody ContractVO contractVo) {
        if (null == contractVo.getContId()) {
            return RespData.fail("参数异常！");
        }
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }
        return contractService.getContract(contractVo.getContId(), loginUser);
    }

    @RequestMapping(value = {"bound-contract"}, method = {RequestMethod.POST})
    @ApiOperation(notes = "获取框架合同和预约申请合同", httpMethod = "POST", value = "获取框架合同和预约申请合同", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<List<Contract>> listCanBeBoundContracts(HttpServletRequest request) {
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            RespData<List<Contract>> beBoundContracts = contractService.listCanBeBoundContracts(loginUser.getOriginId());
            return beBoundContracts;
        } catch (Exception e) {
            logger.error("获取框架合同和预约申请合同>>>", e);
            return RespData.fail("获取框架合同和预约申请合同异常！");
        }
    }

    @RequestMapping(value = {"company-user"}, method = {RequestMethod.POST})
    @ApiOperation(notes = "通过用户获取公司(合同归属公司)", httpMethod = "POST", value = "通过用户获取公司(合同归属公司)", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<List<Department>> getCompanyUserList(HttpServletRequest request) {
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            List<Department> companyList = loginUser.getCompanys();
            return RespData.success(companyList);

        } catch (Exception e) {
            logger.error("请求合同归属公司发生错误>>>", e);
            return RespData.fail("请求合同归属公司发生错误！");
        }
    }

    @RequestMapping(value = {"companyList"}, method = {RequestMethod.POST})
    @ApiOperation(notes = "获取我方单位", httpMethod = "POST", value = "获取我方单位", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<List<Department>> getCompanyList(HttpServletRequest request) {
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            List<Department> companyList = contractService.getCompanyList(loginUser.getOriginId());
            return RespData.success(companyList);

        } catch (Exception e) {
            logger.error("请求合同归属公司发生错误>>>", e);
            return RespData.fail("请求合同归属公司发生错误！");
        }
    }

    @RequestMapping(value = {"dep-user-list"}, method = {RequestMethod.POST})
    @ApiOperation(notes = "获取单位下所有人员", httpMethod = "POST", value = "获取单位下所有人员", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<List<User>> getAllChildUserByCompanyId(HttpServletRequest request, @RequestBody JSONObject companyIdMap) {
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String companyId = companyIdMap.getString("companyId");
            RespData<List<User>> allChildUser = contractService.getAllDepUserByUserId(companyId, loginUser.getUserId());
            return allChildUser;
        } catch (Exception e) {
            logger.error("获取单位下所有人员请求发生错误>>>", e);
            return RespData.fail("通过用户获取单位下所有人员异常！");
        }
    }

    @RequestMapping(value = {"depositPlan-list"}, method = RequestMethod.POST)
    @ApiOperation(notes = "保证金查询", httpMethod = "POST", value = "保证金查询", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<List<ContractDepositPlanVO>> getContractDepositPlanList(HttpServletRequest request, @RequestBody ContractVO contractVo) {
        try {
            if (null == contractVo.getContId()) {
                return RespData.fail("合同ID不能为空！");
            }
            return RespData.success(contractService.getContractDepositPlanList(contractVo));
        } catch (Exception e) {
            logger.error("保证金查询>>>{}", contractVo.getContId(), e);
            return RespData.fail("保证金查询异常！");
        }
    }

    @RequestMapping(value = {"incomeSet-list"}, method = RequestMethod.POST)
    @ApiOperation(notes = "收款计划查询", httpMethod = "POST", value = "收款计划查询", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<List<ContractIncomeSetVO>> getContractIncomeSetList(HttpServletRequest request, @RequestBody ContractVO contractVo) {
        try {
            if (null == contractVo.getContId()) {
                return RespData.fail("合同ID不能为空！");
            }
            return RespData.success(contractService.getContractIncomeSetList(contractVo));
        } catch (Exception e) {
            logger.error("收款计划查询>>>{}", contractVo.getContId(), e);
            return RespData.fail("收款计划查询异常！");
        }
    }


    @RequestMapping(value = {"energyPlanList"}, method = RequestMethod.POST)
    @ApiOperation(notes = "查询能源信息", httpMethod = "POST", value = "查询能源信息", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<List<ContractEnergyPlanVO>> getEnergyPlanList(HttpServletRequest request, @RequestBody ContractVO contractVo) {
        try {
            if (null == contractVo.getContId()) {
                return RespData.fail("合同ID不能为空！");
            }
            return RespData.success(contractService.getEnergyPlanList(contractVo));
        } catch (Exception e) {
            logger.error("查询能源信息>>>{}", contractVo.getContId(), e);
            return RespData.fail("查询能源信息异常！");
        }
    }

    @RequestMapping(value = {"getCostItemList"}, method = {RequestMethod.POST})
    @ApiOperation(notes = "获取费用类型字典列表", httpMethod = "POST", value = "获取费用类型字典列表", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RespData<ContractCostDictsListVO> getCostItemList(HttpServletRequest request) {
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }
        return contractService.getCostItemList(loginUser.getOriginId());
    }


    @PostMapping(value = {"save"})
    @ApiOperation(notes = "保存合同数据", httpMethod = "POST", value = "保存合同数据", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData saveContract(HttpServletRequest request, @RequestBody ContractVO contractVo) {
        if (null == contractVo) {
            return RespData.fail("参数异常！");
        }
        RespData respData = null;
        LoginUser loginUser = null;
        try {
            loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String replace = contractVo.getContDeclare().replace("\n", "<br>");
            contractVo.setContDeclare(replace);
            contractVo.setOrganId(loginUser.getOriginId());
            if (contractVo.getContId() == null) {
                contractVo.setCreateUserId(loginUser.getUserId());
                contractVo.setCreateUserName(loginUser.getUserName());
            }
            respData = contractService.saveContract(contractVo, loginUser);
        } catch (Exception e) {
            logger.error("合同说明转换格式异常>>>>>>>", e.getMessage());
            return RespData.fail(e.toString());
        }
        return respData;
    }
}
