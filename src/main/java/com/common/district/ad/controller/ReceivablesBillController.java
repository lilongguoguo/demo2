package com.common.district.ad.controller;

import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.service.ReceivablesBillDerateService;
import com.common.district.ad.service.ReceivablesBillService;
import com.common.district.ad.vo.ReceiptAmountRequestVo;
import com.common.district.ad.vo.ReceivablesBillDerateReturnVo;
import com.common.district.ad.vo.ReceivablesBillDerateVo;
import com.common.district.ad.vo.ReceivablesBillVo;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.constants.ConstantsEnum;
import com.common.district.common.controller.BaseController;
import com.common.district.common.utils.Pagination;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.model.LoginUser;
import com.common.district.org.model.User;
import com.common.district.org.service.UserService;
import com.common.district.res.model.*;
import com.common.district.res.service.CompanyService;
import com.common.district.res.service.CustomerService;
import com.common.district.res.service.DictsService;
import com.common.district.res.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应收账单接口类
 * @author fc
 */
@Controller
@RequestMapping("api/bill")
@Api( description = "应收账单接口信息" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceivablesBillController extends BaseController {
    @Autowired
    private ReceivablesBillService receivablesBillService;
    @Autowired
    private ReceivablesBillDerateService receivablesBillDerateService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DictsService dictsService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "lst",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收账单管理",  value = "查询列表",httpMethod ="POST",position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<PageInfo<ReceivablesBill>> getReceivablesBill(HttpServletRequest request,@RequestBody ReceivablesBillVo vo){
        try {
            vo.setOrganId(getLoginInfo(request).getOriginId());
            PageInfo build = PageInfo.PageInfoBuilder.anPageInfoDto()
                    .withPages(vo.getCp())
                    .withPageSize(vo.getPs())
                    .withResultList(receivablesBillService.selectReceivablesBillByCondition(vo))
                    .withTotalCount(receivablesBillService.selectReceivablesBillCountByCondition(vo))
                    .build();
            return RespData.success(build);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收账单详情",  value = "账单详情",httpMethod ="POST", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<ReceivablesBill> getReceivablesBillDetail(Long billId)throws Exception{
        return RespData.success(receivablesBillService.selectReceivablesBillDetailByCondition(billId));
    }
    @RequestMapping(value = "derate/lst",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "减免管理",  value = "查询列表",httpMethod ="POST", position = 5, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<PageInfo<ReceivablesBillDerateReturnVo>> getReceivablesBillDerate(HttpServletRequest request,@RequestBody ReceivablesBillDerateVo vo){
        try {
            vo.setOrganId(getLoginInfo(request).getOriginId());
            PageInfo build = PageInfo.PageInfoBuilder.anPageInfoDto()
                    .withPages(vo.getCp())
                    .withPageSize(vo.getPs())
                    .withResultList(receivablesBillDerateService.selectReceivablesBillDerateByCondition(vo))
                    .withTotalCount(receivablesBillDerateService.selectReceivablesBillDerateCountByCondition(vo))
                    .build();
            return RespData.success(build);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "lst/company",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收账单管理-获取合同所属公司",httpMethod ="POST",value = "合同所属公司下拉", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<List<Company>> getReceivablesBillCompany(HttpServletRequest request){
        try {
            String organId=getLoginInfo(request).getOriginId();
            return RespData.success(companyService.selectAllByOrganId(organId));
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "lst/customer",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收账单管理-获取所属公司下的客户",httpMethod ="POST",value = "客户下拉", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<List<Customer>> getReceivablesBillCustomer(HttpServletRequest request,String companyId){
        try {
            String organId=getLoginInfo(request).getOriginId();
            return RespData.success(customerService.getCustomerListByOrganIdAndConLst(StringUtils.isBlank(companyId)?null:new ArrayList<String>(){{this.add(companyId);}},organId));
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "lst/project",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收账单管理-获取所属公司下的项目",httpMethod ="POST",value = "项目下拉", position = 6, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<List<Project>> getReceivablesBillProject(HttpServletRequest request, String companyId){
        try {
            String organId=getLoginInfo(request).getOriginId();
            return RespData.success(projectService.getProjectListByOrganIdAndConLst(StringUtils.isBlank(companyId)?null:new ArrayList<String>(){{this.add(companyId);}},organId));
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "lst/dic",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收账单管理-获取字典租户下的费用名称",httpMethod ="POST",  value = "费用名称", position = 6, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<List<Dicts>> getReceivablesBillDicts(HttpServletRequest request){
        try {
            String organId=getLoginInfo(request).getOriginId();
            return RespData.success(dictsService.getDictsByType(organId, ConstantsEnum.DictsTypeEnum.TYPE1));
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "amount/derate",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收金额减免",httpMethod ="POST",  value = "查询减免列表", position = 7, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<List<ReceivablesBill>> getAmountDerate(HttpServletRequest request,@RequestBody List<String> rbIdList){
        try {
            return RespData.success(receivablesBillService.selectReceivablesBillByRbIdList(rbIdList));
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "selectUserList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "查询人员列表信息",httpMethod ="POST",  value = "人员、部门、角色", position = 8, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<PageInfo<User>> selectUserList(HttpServletRequest request, Integer cp,
                                                                               Integer ps,
                                                                               String userName){
        try {
            String organId=getLoginInfo(request).getOriginId();
            cp=Pagination.getCp(StringUtils.isBlank(cp)?0:cp,ps);
            ps=StringUtils.isBlank(ps)?10:ps;
            PageInfo build = PageInfo.PageInfoBuilder.anPageInfoDto()
                    .withPages(cp)
                    .withPageSize(ps)
                    .withResultList(userService.getUserListByUserName(userName,organId,cp,ps))
                    .withTotalCount(userService.getUserSizeByUserName(userName,organId))
                    .build();
            return RespData.success(build);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "amount/derate/val",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "应收金额校验",httpMethod ="POST",  value = "金额校验", position = 9, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData amountDerateVal(HttpServletRequest request, @RequestBody List<ReceiptAmountRequestVo> vo){
        try {
            return receivablesBillDerateService.amountDerateVal(vo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "violate/derate/val",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "违约金减免校验",httpMethod ="POST",  value = "违约金校验", position = 10, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData violateDerateVal(HttpServletRequest request, @RequestBody List<ReceiptAmountRequestVo> vo){
        try {
            return receivablesBillDerateService.violateDerateVal(vo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "amount/derate/save",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "保存应收金额和流程信息",httpMethod ="POST",  value = "应收流程保存", position = 11, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData amountDerateSave(HttpServletRequest request, @RequestBody List<ReceiptAmountRequestVo> vo){
        try {
            LoginUser loginInfo = getLoginInfo(request);
            return receivablesBillDerateService.amountDerateSave(vo,loginInfo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    @RequestMapping(value = "violate/derate/save",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "保存违约金和流程信息",httpMethod ="POST",  value = "违约金流程保存", position = 12, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData violateDerateSave(HttpServletRequest request, @RequestBody List<ReceiptAmountRequestVo> vo){
        try {
            LoginUser loginInfo = getLoginInfo(request);
            return receivablesBillDerateService.violateDerateSave(vo,loginInfo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
}
