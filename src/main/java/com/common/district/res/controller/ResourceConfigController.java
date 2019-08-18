package com.common.district.res.controller;

import com.common.district.common.controller.BaseController;
import com.common.district.common.utils.JsonResult;
import com.common.district.org.model.LoginUser;
import com.common.district.res.dto.ResourceTypeDTO;
import com.common.district.res.service.ResourceConfigService;
import com.common.district.res.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 14:14 2019/7/29
 */

@Controller
@RequestMapping(value = "resConfig", method = RequestMethod.POST)
@Api(description = "功能配置")
public class ResourceConfigController extends BaseController {

    @Autowired
    private ResourceConfigService resourceConfigService;

    @ApiOperation(notes = "根据级别查询资源", httpMethod = "POST", value = "根据级别查询资源", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("getResList")
    @ResponseBody
    public JsonResult getResList(HttpServletRequest request, @RequestBody ResLevelVo resLevel) {
        JsonResult jsonResult = new JsonResult();

        try {
            LoginUser loginInfo = getLoginInfo(request);
            List<ResourceTypeDTO> resourceTypeList = resourceConfigService.getResourceTypeList(loginInfo.getOriginId(), resLevel.getLevel());
            jsonResult.setResult(resourceTypeList);
            jsonResult.setSuccess(true);
            jsonResult.setMessage("获取成功");
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("获取失败");
        }

        return jsonResult;
    }

    @ApiOperation(notes = "查询子级资源", httpMethod = "POST", value = "查询子级资源", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("getSubResList")
    @ResponseBody
    public JsonResult getSubResList(HttpServletRequest request, @RequestBody AssociationResVo associationResVo) {
        JsonResult jsonResult = new JsonResult();

        try {
            LoginUser loginInfo = getLoginInfo(request);
            List<ResourceTypeDTO> resourceTypeList = resourceConfigService.getSubResourceTypeList(loginInfo.getOriginId(), associationResVo.getParentId());
            jsonResult.setResult(resourceTypeList);
            jsonResult.setSuccess(true);
            jsonResult.setMessage("获取成功");
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("获取失败");
        }

        return jsonResult;
    }

    @ApiOperation(notes = "删除资源", httpMethod = "POST", value = "删除资源", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("removeRes")
    @ResponseBody
    public JsonResult removeRes(HttpServletRequest request, @RequestBody ResDelVo resDel) {
        JsonResult jsonResult = new JsonResult();

        try {
            LoginUser loginInfo = getLoginInfo(request);
            //"ZYLX201907291551332341"
            boolean flg = resourceConfigService.removeResource(loginInfo.getOriginId(), resDel.getId());
            //boolean flg = true;
            if (flg) {
                jsonResult.setResult(flg);
                jsonResult.setSuccess(true);
                jsonResult.setMessage("获取成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("获取失败");
            }
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("获取失败");
        }

        return jsonResult;
    }

    @ApiOperation(notes = "资源名称判重", httpMethod = "POST", value = "资源名称判重", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("checkResName")
    @ResponseBody
    public JsonResult checkResName(HttpServletRequest request, @RequestBody ResCheckVo resCheckVo) {
        JsonResult jsonResult = new JsonResult();

        try {
            LoginUser loginInfo = getLoginInfo(request);
            boolean flg = resourceConfigService.checkResourceByName(loginInfo.getOriginId(), resCheckVo.getName());
            if (flg) {
                jsonResult.setResult(flg);
                jsonResult.setSuccess(true);
                jsonResult.setMessage("可以添加");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("资源名称已被使用");
            }
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("资源名称已被使用");
        }

        return jsonResult;
    }

    @ApiOperation(notes = "添加资源", httpMethod = "POST", value = "添加资源", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("addResource")
    @ResponseBody
    public JsonResult addResource(HttpServletRequest request, @RequestBody ResAddVo addVo) {
        JsonResult jsonResult = new JsonResult();

        try {
            LoginUser loginInfo = getLoginInfo(request);
            boolean flg = resourceConfigService.addResourceByName(loginInfo.getOriginId(), addVo.getName(), addVo.getLevel());
            if (flg) {
                jsonResult.setResult(flg);
                jsonResult.setSuccess(true);
                jsonResult.setMessage("添加成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("添加失败");
            }
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("添加失败");
        }

        return jsonResult;
    }

    @ApiOperation(notes = "添加关联二级资源", httpMethod = "POST", value = "添加关联二级资源", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("addChildResource")
    @ResponseBody
    public JsonResult addChildResource(HttpServletRequest request, @RequestBody ResourceTypeVO resourceTypeVO) {
        JsonResult jsonResult = new JsonResult();

        try {
            LoginUser loginInfo = getLoginInfo(request);
            boolean flg = resourceConfigService.addChildResource(loginInfo.getOriginId(), resourceTypeVO);
            if (flg) {
                jsonResult.setResult(flg);
                jsonResult.setSuccess(true);
                jsonResult.setMessage("添加成功");
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMessage("添加失败");
            }
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("添加失败");
        }

        return jsonResult;
    }

    @ApiOperation(notes = "查询关联资源", httpMethod = "POST", value = "查询关联资源", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("getAssociationRes")
    @ResponseBody
    public JsonResult getAssociationRes(HttpServletRequest request, @RequestBody AssociationResVo associationResVo) {
        JsonResult jsonResult = new JsonResult();

        try {
            LoginUser loginInfo = getLoginInfo(request);
            List<ResourceTypeDTO> resourceTypeList = resourceConfigService.getAssociationRes(loginInfo.getOriginId(), associationResVo.getParentId());
            jsonResult.setResult(resourceTypeList);
            jsonResult.setSuccess(true);
            jsonResult.setMessage("获取成功");
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMessage("获取失败");
        }

        return jsonResult;
    }
}
