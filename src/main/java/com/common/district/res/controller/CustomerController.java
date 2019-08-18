package com.common.district.res.controller;

import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.common.utils.Pagination;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.model.LoginUser;
import com.common.district.res.model.Customer;
import com.common.district.res.model.CustomerContact;
import com.common.district.res.service.CustomerService;
import com.common.district.res.vo.CustomerContactVO;
import com.common.district.res.vo.CustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("customer")
@Api( description = "客户管理")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation("客户管理-客户列表")
    @RequestMapping(value = "getCustomerList", method = RequestMethod.POST)
    @ResponseBody
    public RespData<PageInfo<Customer>> getCustomerList(HttpServletRequest request,@RequestBody CustomerVO customerVO){
        try {
            LoginUser loginUser = getLoginInfo(request);
            String organId = loginUser.getOriginId();
            return RespData.success(PageInfo.PageInfoBuilder.anPageInfoDto()
                    .withPages(customerVO.getPageNum())
                    .withPageSize(customerVO.getPageSize())
                    .withResultList(customerService.getCustomerList(customerVO, organId, Pagination.getCp(customerVO.getPageNum(), customerVO.getPageSize()), customerVO.getPageSize()))
                    .withTotalCount(customerService.getCustomerListCount(customerVO, organId))
                    .build());
        }catch (Exception e){
            logger.error("getCustomerList失败{}" + e.getMessage());
            return RespData.fail("获取客户查询列表失败");
        }
    }

    @ApiOperation("客户管理-新增或修改客户")
    @RequestMapping(value = "saveOrUpdateCustomer", method = RequestMethod.POST)
    @ResponseBody
    public RespData saveCustomer(HttpServletRequest request,@RequestBody CustomerVO customerVO){
        try {

            LoginUser loginUser = getLoginInfo(request);
            if (StringUtils.isBlank(customerVO.getId())){
                customerVO.setCreateUserId(loginUser.getUserId());
                customerVO.setCreateUserName(loginUser.getUserName());
//                customerVO.setDepartId(loginUser.getDepartments().get(0).getDeptId());
//                customerVO.setDepartName(loginUser.getDepartments().get(0).getDeptName());
//                customerVO.setCreateCompanyId(loginUser.getCompanys().get(0).getCompanyId());
//                customerVO.setCreateCompanyName(loginUser.getCompanys().get(0).getCompanyName());
            }else {
                customerVO.setUpdateUserId(loginUser.getUserId());
                customerVO.setUpdateUserName(loginUser.getUserName());
            }
            return customerService.saveCustomerAndContact(customerVO, loginUser.getOriginId());
        } catch (Exception e) {
            logger.error("保存客户信息异常" + e.getMessage());
            return RespData.fail("保存客户信息异常");
        }
    }

    @ApiOperation("客户管理-新增或修改客户联系人")
    @RequestMapping(value = "saveOrUpdateCustomerContact", method = RequestMethod.POST)
    @ResponseBody
    public RespData saveOrUpdateCustomerContact(HttpServletRequest request,@RequestBody CustomerContactVO customerContactVO){
        try {
            return customerService.saveOrUpdateCustomerContact(customerContactVO);
        } catch (Exception e) {
            logger.error("保存客户联系人信息异常" + e.getMessage());
            return RespData.fail("保存客户联系人信息异常");
        }
    }

    @ApiOperation("客户管理-获取客户详情")
    @RequestMapping(value = "getCustomerDetail", method = RequestMethod.POST)
    @ResponseBody
    public RespData getCustomerDetail(HttpServletRequest request,@RequestParam(value = "id") Long id){
        if (StringUtils.isNotBlank(id)){
            return RespData.success(customerService.getCustomerDetail(id));
        }
        return RespData.fail("获取客户信息失败");
    }

    @ApiOperation("客户管理-删除客户")
    @RequestMapping(value = "delCustomer", method = RequestMethod.POST)
    @ResponseBody
    public RespData delCustomer(HttpServletRequest request,@RequestParam(value = "id") Long id){
        if (StringUtils.isNotBlank(id)){
            return customerService.delCustomer(id);
        }
        return RespData.fail("获取客户信息失败");
    }

    /**
     *
     * @desc 通过租户获取当前客户列表数据
     */
    @PostMapping("cust-hList")
    @ResponseBody
    @ApiOperation(notes = "获取客户列表", httpMethod = "POST", value = "获取客户列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<PageInfo<HashMap<String,Object>>> getCusIdAndName(HttpServletRequest request , @RequestBody CustomerVO customerVO) {
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            PageInfo<HashMap<String,Object>> pageInfo = customerService.getCusIdAndName(loginUser.getOriginId(),customerVO);
            return RespData.success(pageInfo);
        } catch (Exception e) {
            logger.error("请求getCusList发生异常>>>>>", e);
            return RespData.fail("请求客户列表发生错误！请重试！");
        }
    }

    /**
     *
     * @desc 通过租户获取当前客户列表数据
     */
    @PostMapping("cust-cont-list")
    @ResponseBody
    @ApiOperation(notes = "获取客户联系人列表", httpMethod = "POST", value = "获取客户联系人列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<List<CustomerContact>> getCustomerContactList(HttpServletRequest request, @RequestBody CustomerVO customerVO) {
        RespData<List<CustomerContact>> customerContactList = null;
        try {
            customerContactList = customerService.getCustomerContactList(customerVO.getId());
            return customerContactList;
        } catch (Exception e) {
            logger.error("请求getCustomerContactList发生异常>>>>>", e);
            return RespData.fail("请求客户列表发生错误！请重试！");
        }
    }

}
