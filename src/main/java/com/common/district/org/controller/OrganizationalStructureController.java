package com.common.district.org.controller;

import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import com.common.district.org.service.DepartmentService;
import com.common.district.org.vo.ResDeptVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * 组织架构
 */
@Controller
@RequestMapping("framework")
@Api(description = "组织架构信息")
public class OrganizationalStructureController extends BaseController {

    private static final String NewlyAdded = "新增检查参数";
    private static final String Delete = "删除检查参数";
    private static final String UpdateParameter = "修改检查参数";

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询组织框架
     * （查询部门树）
     */
    @RequestMapping(value = "getOrganizationalStructure", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "组织架构信息查询", httpMethod = "POST", value = "组织架构信息查询", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<Department> getOrganizationalStructure(HttpServletRequest request) {
        RespData resp = null;
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }

        try {
            //查询组织框架（查询所有部门，到顶级部门）
            resp = departmentService.getAllDepartment();
        } catch (Exception e) {
            logger.error("组织架构信息查询异常" + e.getMessage());
            return RespData.fail("组织架构信息查询异常");
        }
        return resp;
    }

    /**
     * 新增组织框架
     */
    @RequestMapping(value = "saveOrganizationalStructure", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "新增组织框架", httpMethod = "POST", value = "新增组织框架", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData saveOrganizationalStructure(HttpServletRequest request,
                                                @RequestBody ResDeptVo resDeptVo) {
        RespData resp = null;
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }

        if(resDeptVo.getOnOff()){//判断是新增还是修改 true为修改
            //组装参数
            Department department = new Department();
            String organId = loginUser.getOriginId();
            department.setOriginId(organId);//租户id
            department.setDeptId(resDeptVo.getDeptId());//部门id
            department.setDeptName(resDeptVo.getDeptName());//部门姓名
            department.setOrganizationCode(resDeptVo.getCoding());//机构编码
            department.setRemark(resDeptVo.getCustomerName());//备注
            department.setDeptType(resDeptVo.getDeptType());//部门类型
            department.setUpdateTime(new Date());//修改时间
            department.setUpdateUserId(loginUser.getUserId());//修改人id
            department.setParentId(resDeptVo.getParentId());//上级机构id
            Boolean isQualified = checkParameters(department, UpdateParameter);//新增检查参数是否合格
            if (!isQualified) {
                return RespData.fail("修改操作参数异常");
            }
            resp = departmentService.revisionDepartment(department);
        }else{//新增
        //组装参数
        Department department = new Department();
        department.setDeptId(UUID.randomUUID().toString());//随机生成一个部门id
        department.setDeptName(resDeptVo.getDeptName());//部门姓名
        department.setParentId(resDeptVo.getParentId());
        department.setOrganizationCode(resDeptVo.getCoding());//机构编码
        department.setRemark(resDeptVo.getCustomerName());//备注
        department.setDel(0);//默认不删除
        department.setStatus(1);//开启
        department.setDeptType(resDeptVo.getDeptType());//部门类型
        department.setCreateUserId(loginUser.getUserId());//创建人id
        department.setCreateTime(new Date());//创建时间
        department.setOriginId(loginUser.getOriginId());//租户id
        Boolean isQualified = checkParameters(department, NewlyAdded);//新增检查参数是否合格
        if (!isQualified) {
            return RespData.fail("新增操作参数异常");
        }
        try {
            resp = departmentService.saveDepartment(department);
        } catch (Exception e) {
            logger.error("新增组织框架异常" + e.getMessage());
            return RespData.fail("新增组织框架异常");
        }

        }
        return resp;
    }

    /**
     * 删除组织框架
     */
    @RequestMapping(value = "deleteOrganizationalStructure", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "删除组织框架", httpMethod = "POST", value = "删除组织框架", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData deleteOrganizationalStructure(HttpServletRequest request, @RequestBody ResDeptVo resDeptVo) {
        RespData resp = null;
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }
        //组装参数
        Department department = new Department();
        department.setDeptId(resDeptVo.getDeptId());//删除的部门id
        department.setDelUserId(loginUser.getUserId());
        Boolean isQualified = checkParameters(department, Delete);//删除检查参数是否合格
        if (!isQualified) {
            return RespData.fail("删除操作参数异常");
        }
        try {
            resp = departmentService.deleteDepartment(department);
        } catch (Exception e) {
            logger.error("删除组织框架异常" + e.getMessage());
            return RespData.fail("删除组织框架异常");
        }

        return resp;
    }

    /**
     * 获取字典表里面的部门类型
     */
    @RequestMapping(value = "getDepartmentOfType", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "查询字典表里面的部门类型", httpMethod = "POST", value = "查询字典表里面的部门类型", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getDepartmentOfType(HttpServletRequest request) {
        RespData resp = null;
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }
        try {
            //租户id
            String organId = loginUser.getOriginId();
            resp = departmentService.getDepartmentOfType(organId);
        } catch (Exception e) {
            logger.error("查询字典表里面的部门类型异常" + e.getMessage());
            return RespData.fail("查询字典表里面的部门类型异常");
        }
        return resp;
    }

    /**
     * 修改部门信息
     */
    @RequestMapping(value = "revisionDepartment", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "修改部门信息", httpMethod = "POST", value = "修改部门信息", position = 5, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData revisionDepartment(HttpServletRequest request, @RequestBody Department department) {
        RespData resp = null;
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }
        try {
            //租户id
            String organId = loginUser.getOriginId();
            department.setOriginId(organId);
            resp = departmentService.revisionDepartment(department);
        } catch (Exception e) {
            logger.error("修改部门信息异常" + e.getMessage());
            return RespData.fail("修改部门信息异常");
        }
        return resp;
    }


    /**
     * 检查部门参数是否合格(true合格、false不合格)
     */
    private static Boolean checkParameters(Department department, String Parameters) {
        Boolean isCheckParameters = false;
        switch (Parameters) {
            case NewlyAdded: {
                //新增检查,部门名不为空，上级机构id不为空，部门类型不为空。
                isCheckParameters = StringUtils.isNotEmpty(department.getDeptName()) && StringUtils.isNotEmpty(department.getParentId()) && department.getDeptType() != null;
                break;
            }
            case Delete: {
                //删除检查,部门id不能为空和删除人id不能为空。
                isCheckParameters = StringUtils.isNotEmpty(department.getDeptId()) && StringUtils.isNotEmpty(department.getDelUserId());
                break;
            }
            case UpdateParameter:{
                //修改参数检查（部门id不能为空）
                isCheckParameters = StringUtils.isNotEmpty(department.getDeptId()) &&
                        StringUtils.isNotEmpty(department.getDeptName()) && StringUtils.isNotEmpty(department.getParentId()) && department.getDeptType() != null;
                break;
            }
            default:
                break;
        }
        return isCheckParameters;
    }


}
