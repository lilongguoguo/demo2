package com.common.district.res.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.org.model.LoginUser;
import com.common.district.res.service.SelectToolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 下拉框后台查询数据
 */
@RestController
@RequestMapping("api/tool")
@Api( description = "下拉框后台查询接口" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class SelectToolController extends BaseController {

    @Autowired
    private SelectToolService selectToolService;

    @PostMapping(value="resource")
    @ApiOperation(notes = "资源类型列表查询", httpMethod = "POST", value = "资源类型列表查询", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getResourceType(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String parentId = map.getString("parentId");
            String organId = loginUser.getOriginId();
            resp = selectToolService.getResourceType(organId, parentId);
        } catch (Exception e) {
            logger.error("获取资源类型数据异常:" + e.getMessage());
            return RespData.fail("获取资源类型数据异常！");
        }
        return resp;
    }

    @PostMapping(value="dicts")
    @ApiOperation(notes = "根据类型获取字典数据查询", httpMethod = "POST", value = "根据类型获取字典数据查询", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getDictData(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String type = map.getString("type");
            String organId = loginUser.getOriginId();
            resp = selectToolService.getDictData(organId, type);
        } catch (Exception e) {
            logger.error("获取字典数据异常:" + e.getMessage());
            return RespData.fail("获取字典数据异常！");
        }
        return resp;
    }

    @PostMapping(value="companyList")
    @ApiOperation(notes = "获取公司列表", httpMethod = "POST", value = "获取公司列表", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getCompany(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String organId = loginUser.getOriginId();
            resp = selectToolService.getCompany(organId);
        } catch (Exception e) {
            logger.error("获取公司列表异常:" + e.getMessage());
            return RespData.fail("获取公司列表异常！");
        }
        return resp;
    }

    @PostMapping(value="districtList")
    @ApiOperation(notes = "获取片区列表", httpMethod = "POST", value = "根据公司获取片区列表", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getDistrict(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String companyId = map.getString("companyId");
            String organId = loginUser.getOriginId();
            resp = selectToolService.getDistrict(organId, companyId);
        } catch (Exception e) {
            logger.error("获取片区列表异常:" + e.getMessage());
            return RespData.fail("获取片区列表异常！");
        }
        return resp;
    }

    @PostMapping(value="projectList")
    @ApiOperation(notes = "获取项目列表", httpMethod = "POST", value = "根据片区获取项目列表", position = 5, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getProject(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String areaId = map.getString("areaId");
            String organId = loginUser.getOriginId();
            resp = selectToolService.getProject(organId, areaId);
        } catch (Exception e) {
            logger.error("获取项目列表异常:" + e.getMessage());
            return RespData.fail("获取项目列表异常！");
        }
        return resp;
    }

    @PostMapping(value="provinceList")
    @ApiOperation(notes = "获取省级列表", httpMethod = "POST", value = "获取省级列表", position = 6, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getProvince(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            resp = selectToolService.getProvince();
        } catch (Exception e) {
            logger.error("获取省级列表异常:" + e.getMessage());
            return RespData.fail("获取省级列表异常！");
        }
        return resp;
    }

    @PostMapping(value="regionChildList")
    @ApiOperation(notes = "获取城市子级列表", httpMethod = "POST", value = "获取城市子级列表", position = 6, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getChildRegionList(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String regionParentId = map.getString("regionParentId");
            resp = selectToolService.getChildRegionList(regionParentId);
        } catch (Exception e) {
            logger.error("获取省级列表异常:" + e.getMessage());
            return RespData.fail("获取省级列表异常！");
        }
        return resp;
    }

    /*@PostMapping(value="businessType")
    @ApiOperation(notes = "物业业态下拉", httpMethod = "POST", value = "物业业态下拉", position = 6, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getbusinessType(HttpServletRequest request, @RequestBody JSONObject map) {
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            String dictsType = map.getString("dictsType");
            resp = selectToolService.getBusinessType(loginUser.getOriginId(),dictsType);
        } catch (Exception e) {
            logger.error("获取省级列表异常:" + e.getMessage());
            return RespData.fail("获取省级列表异常！");
        }
        return resp;
    }*/
}
