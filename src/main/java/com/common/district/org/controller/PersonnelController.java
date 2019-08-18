package com.common.district.org.controller;

import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.org.model.LoginUser;
import com.common.district.org.service.PersonnelService;
import com.common.district.org.vo.UserVo;
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
import java.util.List;

@Controller
@RequestMapping("Personnel")
@Api(description = "人员账号信息")
public class PersonnelController extends BaseController {

    @Autowired
    PersonnelService personnelService;

    /**
     * 人员账号列表
     */
    @RequestMapping(value = "getPersonnelList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "人员账号列表", httpMethod = "POST", value = "人员账号列表", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getPersonnelList(HttpServletRequest request, @RequestBody UserVo vo) {
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            vo.setOriginId(loginUser.getOriginId());
            Integer a = personnelService.getPersonnelListCount(vo);
            List list = personnelService.getPersonnelList(vo);
            PageInfo build = PageInfo.PageInfoBuilder.anPageInfoDto()
                    .withPages(vo.getCp())
                    .withPageSize(vo.getPs())
                    .withResultList(personnelService.getPersonnelList(vo))
                    .withTotalCount(personnelService.getPersonnelListCount(vo))
                    .build();
            return RespData.success(build);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }
    }
    /**
     * 部门角色名
     */
    @RequestMapping(value = "getDeptRoleName", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "部门角色名", httpMethod = "POST", value = "部门角色名", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getDeptRoleName(HttpServletRequest request, @RequestBody UserVo vo) {

        try{
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            vo.setOriginId(loginUser.getOriginId());
            return personnelService.getDeptRoleName(vo);
        }catch (Exception e){
            logger.error(e.getMessage());
            return RespData.fail("查询异常");
        }

    }







}
