package com.common.district.res.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.common.utils.JsonResult;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.model.LoginUser;
import com.common.district.res.model.Position;
import com.common.district.res.model.Project;
import com.common.district.res.service.ProjectService;
import com.common.district.res.vo.PositionVO;
import com.common.district.res.vo.ProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 11:42 2019/8/12
 */
@Controller
@RequestMapping("api/project")
@Api( description = "项目接口信息" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value="projectList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "项目列表查询", httpMethod = "POST", value = "项目列表查询", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getProjectList(HttpServletRequest request, @RequestBody ProjectVo projectVo){
        PageInfo<ProjectVo> pageInfo = null;
        try {

            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            // 租户id
            projectVo.setOrganId(loginUser.getOriginId());
            pageInfo = projectService.selectPageList(projectVo);
        } catch (Exception e) {
            logger.error("getProjectList失败{}" + e.getMessage());
            return RespData.fail("获取项目列表失败");
        }
        return RespData.success(pageInfo);
    }

    @RequestMapping(value="projectExport",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "项目列表导出", httpMethod = "POST", value = "项目列表导出", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    public void projectExport(HttpServletRequest request, HttpServletResponse response, @RequestBody ProjectVo projectVo){
        try {
            LoginUser loginUser = getLoginInfo(request);
            // 租户id
            projectVo.setOrganId(loginUser.getOriginId());
            projectService.projectExport(response, projectVo);
        } catch (Exception e) {
            logger.error("点位导出异常" + e.getMessage());
        }
    }

    @RequestMapping(value="deleteProject",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "删除项目", httpMethod = "POST", value = "删除项目", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData deletePosition(HttpServletRequest request, @RequestBody ProjectVo projectVo){
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            resp = projectService.deleteProject(loginUser, projectVo.getProjectId());
        } catch (Exception e) {
            logger.error("删除项目异常" + e.getMessage());
            return RespData.fail("删除项目异常");
        }
        return resp;
    }

    @RequestMapping(value = "saveProject",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "保存项目信息(新增、修改)", httpMethod = "POST", value = "保存项目信息(新增、修改)", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData positionSave(HttpServletRequest request, @RequestBody Project project){
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            if (StringUtils.isNotBlank(project.getProjectId())) {
                project.setUpdateUserId(loginUser.getUserId());
                project.setUpdateTime(new Date());
            }  else {
                project.setCreateUserId(loginUser.getUserId());
                project.setCreateTime(new Date());
            }
            project.setOrganId(loginUser.getOriginId());
            resp = projectService.saveProject(project);
        } catch (Exception e) {
            logger.error("保存项目信息异常" + e.getMessage());
            return RespData.fail("保存项目信息异常");
        }
        return resp;
    }

    @RequestMapping(value = "projectDetail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "项目详情", httpMethod = "POST", value = "项目详情", position = 5, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getProjectDetail(HttpServletRequest request, @RequestBody Project project){
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            project.setOrganId(loginUser.getOriginId());
            resp = projectService.getProjectDetail(project.getProjectId());
        } catch (Exception e) {
            logger.error("查询项目详情异常" + e.getMessage());
            return RespData.fail("查询项目详情异常");
        }
        return resp;
    }
}
