package com.common.district.org.controller;

import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.org.model.LoginUser;
import com.common.district.org.model.Role;
import com.common.district.org.service.RoleService;
import com.common.district.org.vo.BusRoleVo;
import com.common.district.org.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色设置
 */
@Controller
@RequestMapping("CharacterSet")
@Api(description = "角色设置信息")
public class CharacterSetController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询角色设置信息
     */
    @RequestMapping(value = "getCharacterSet", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "查询角色设置信息", httpMethod = "POST", value = "查询角色设置信息", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getCharacterSet(HttpServletRequest request, @RequestBody RoleVo vo) {
        RespData resp = null;
        LoginUser loginUser = getLoginInfo(request);
        if (loginUser == null) {
            return RespData.fail("登录已过期，请重新登录!");
        }
        try {
            vo.setOriginId(BaseController.getLoginInfo(request).getOriginId());
            PageInfo build = PageInfo.PageInfoBuilder.anPageInfoDto()
                    .withPages(vo.getCp())
                    .withPageSize(vo.getPs())
                    .withResultList(roleService.getAllRole(vo))
                    .withTotalCount(roleService.countRole(vo))
                    .build();
            return RespData.success(build);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }

    /**
     * 查询角色设置配置（添加或修改查询）
     * 步骤：
     *  1.判断页面传过来是新增角色设置，还是修改角色设置
     *  2.如果是新增角色设置，则遍历菜单功能表回显页面
     *  3.如果是修改，则回显菜单并展示哪些菜单已勾选
     *
     */
    @RequestMapping(value = "characterSet", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "查询角色设置配置", httpMethod = "POST", value = "查询角色设置配置", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData characterSet(HttpServletRequest request, @RequestBody BusRoleVo vo) {
        try{
            RespData resp = null;
            vo.setOrganId(BaseController.getLoginInfo(request).getOriginId());//设置租户id
            return roleService.characterSet(vo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }

    }


    /**
     * 添加或者修改角色菜单功能
     */
    @RequestMapping(value = "appendCharacterSet", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "添加菜单功能设置", httpMethod = "POST", value = "添加菜单功能设置", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData appendCharacterSet(HttpServletRequest request, @RequestBody BusRoleVo vo) {
        try{
            RespData resp = null;
            vo.setOrganId(BaseController.getLoginInfo(request).getOriginId());//设置租户id
            vo.setCreateUserId(BaseController.getLoginInfo(request).getUserId());//创建用户id
            return roleService.appendCharacterSet(vo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }




}
