package com.common.district.res.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.district.common.controller.BaseController;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.LoginUser;
import com.common.district.res.model.Position;
import com.common.district.res.service.PositionService;
import com.common.district.res.vo.ContractPositionVO;
import com.common.district.res.vo.PositionImportVO;
import com.common.district.res.vo.PositionSalesControVO;
import com.common.district.res.vo.PositionVO;
import com.common.district.res.vo.UploadLogVO;
import com.common.district.res.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("api/position")
@Api( description = "点位接口信息" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController extends BaseController {
    @Autowired
    private PositionService positionService;

    @RequestMapping(value="getPositionPageList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "点位信息列表查询", httpMethod = "POST", value = "点位信息列表查询", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<PageInfo<PositionVO>> getPositionPageList(HttpServletRequest request, @RequestBody PositionVO positionVO){
        PageInfo<PositionVO> pageInfo = null;
        try {

            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            // 租户id
            positionVO.setOrganId(loginUser.getOriginId());
            pageInfo = positionService.selectPageList(positionVO);
        } catch (Exception e) {
            logger.error("getPositionPageList失败{}" + e.getMessage());
            return RespData.fail("获取点位查询列表失败");
        }
        return RespData.success(pageInfo);
    }
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "保存点位信息(新增、编辑)", httpMethod = "POST", value = "保存点位信息", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData positionSave(HttpServletRequest request, @RequestBody Position position){
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            if (position.getId() != null) {
                position.setUpdateUserId(loginUser.getUserId());
                position.setUpdateUserName(loginUser.getUserName());
            }  else {
                position.setCreateUserId(loginUser.getUserId());
                position.setCreateUserName(loginUser.getUserName());
            }
            position.setOrganId(loginUser.getOriginId());
            resp = positionService.positionSave(position);
        } catch (Exception e) {
            logger.error("保存点位信息异常" + e.getMessage());
            return RespData.fail("保存点位异常");
        }
        return resp;
    }

    @RequestMapping(value="deletePosition",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "删除点位", httpMethod = "POST", value = "删除点位", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData deletePosition(HttpServletRequest request, @RequestBody JSONObject map){
        RespData resp = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            Long id = map.getLongValue("id");
            String userName = loginUser.getUserName();
            String userId = loginUser.getUserId();
            resp = positionService.deletePosition(id, userId, userName);
        } catch (Exception e) {
            logger.error("删除点位异常" + e.getMessage());
            return RespData.fail("删除点位异常");
        }
        return resp;
    }

    @RequestMapping(value="positionImport",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "点位导入", httpMethod = "POST", value = "点位导入", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData positionImport(HttpServletRequest request, HttpServletResponse response, @RequestBody PositionImportVO positionImportVO){
        RespData resp = null;
        try {
            LoginUser loginInfo = getLoginInfo(request);
            //resp = positionService.positionImport(exportfile,"ZYLX201907291551332341");
            resp = positionService.positionImport(positionImportVO.getFileUrl(),loginInfo,request);
        } catch (Exception e) {
            logger.error("点位导入异常" + e.getMessage());
            return RespData.fail("点位导入异常");
        }
        return resp;
    }

    @RequestMapping(value="positionExport",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "点位导出", httpMethod = "POST", value = "点位导出", position = 5, produces = MediaType.APPLICATION_JSON_VALUE)
    public void positionExport(HttpServletRequest request, HttpServletResponse response, @RequestBody PositionVO positionVO){
        try {
            LoginUser loginUser = getLoginInfo(request);
            // 租户id
            positionVO.setOrganId(loginUser.getOriginId());
            positionService.positionExport(response, positionVO);
        } catch (Exception e) {
            logger.error("点位导出异常" + e.getMessage());
        }
    }

    /**
     * 下载点位导入模板
     * @param request
     * @param response
     * @param fileName
     * @param postfix
     */
    @ResponseBody
    @ApiOperation(notes = "点位导入模板下载", httpMethod = "GET", value = "点位导入模板下载", position = 6, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/downLoadFile")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName") String fileName,@RequestParam("postfix")String postfix) {
        String fName = fileName + "." + postfix;
        String fPth="/template/" + fName;
        try {
            positionService.downLoadFile(request,response,fName,fPth);
        } catch (Exception e) {
            logger.error("下载点位导入模板异常" + e.getMessage());
        }
    }

    /**
     * 查询点位导入记录
     */
    @ResponseBody
    @ApiOperation(notes = "查询点位导入记录", httpMethod = "POST", value = "查询点位导入记录", position = 7, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/getPosLogList")
    public RespData<PageInfo<UploadLogVO>> getPosLogList(HttpServletRequest  request,@RequestBody UploadLogVO searchData) {
        PageInfo<UploadLogVO> posLogList = null;
        try {
            LoginUser loginInfo = getLoginInfo(request);
            //uploadLogVO.setOrganId("ZYLX201907291551332341");
            searchData.setOrganId(loginInfo.getOriginId());
            searchData.setType(1);
            String[] updataTime = searchData.getUploadDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            if(updataTime.length==2){
                String start= updataTime[0].replace("Z", " UTC");
                String end= updataTime[1].replace("Z", " UTC");

                searchData.setCreateTimeStart(sdf.parse(start));
                searchData.setCreateTimeEnd(sdf.parse(end));
            }
            posLogList = positionService.getPosLogList(searchData);
        } catch (Exception e) {
            logger.error("查询列表失败{}" + e.getMessage());
            return RespData.fail("查询异常");
        }

        return RespData.success(posLogList);
    }

    /**
     * 上传文件
     * @param request 待上传文件列表
     * @param request 请求
     * @return 上传完成后返回存储路径
     * @throws IOException
     */
    @ApiOperation(notes = "文件上传", httpMethod = "POST", value = "文件上传", position = 8, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public RespData uploadFiles(HttpServletRequest request) {
        RespData respData = null;
        try {
            LoginUser loginInfo = getLoginInfo(request);
            respData =positionService.uploadFile(request,loginInfo);

        } catch (Exception e) {
            logger.error("文件上传失败{}" + e.getMessage());
            return RespData.fail("文件上传失败");
        }
        return respData;
    }

    /**
     * @description:(合同使用) 此接口使用于合同选择空闲点位和查看合同已选点位）
     */
    @PostMapping("/freeList")
    @ResponseBody
    @ApiOperation(notes = "查询相关信息", httpMethod = "POST", value = "查询点位列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<PageInfo<PositionVO>> getFreePositionDtoList(HttpServletRequest  request, @RequestBody ContractPositionVO positionSearchVO) {
        PageInfo<PositionVO> pageInfo = null;
        try {
            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            positionSearchVO.setOrganId(loginUser.getOriginId());
            pageInfo = positionService.getFreePositionList(positionSearchVO);
        } catch (Exception e) {
            logger.error("获取点位列表失败" + e.getMessage());
            return RespData.fail("获取查询列表失败");
        }
        return RespData.success(pageInfo);

    }

	@RequestMapping(value="getPositionSaleContrList",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "点位销控列表查询", httpMethod = "POST", value = "点位销控列表查询", position = 9, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData<PageInfo<PositionSalesControVO>> getPositionSaleContrList(HttpServletRequest request, @RequestBody PositionSalesControVO positionSalesControVO){
        PageInfo<PositionSalesControVO> pageInfo = null;
        try {

            LoginUser loginUser = getLoginInfo(request);
            if (loginUser == null) {
                return RespData.fail("登录已过期，请重新登录!");
            }
            // 租户id
            positionSalesControVO.setOrganId(loginUser.getOriginId());
            pageInfo = positionService.selectSaleControlPageList(positionSalesControVO);
        } catch (Exception e) {
            logger.error("getPositionSaleContrList失败{}" + e.getMessage());
            return RespData.fail("获取点位销控列表失败");
        }
        return RespData.success(pageInfo);
    }

    @RequestMapping(value="positionSaleControExport",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "点位销控列表导出", httpMethod = "POST", value = "点位销控列表导出", position = 10, produces = MediaType.APPLICATION_JSON_VALUE)
    public void positionSaleControExport(HttpServletRequest request, HttpServletResponse response, @RequestBody PositionSalesControVO positionSalesControVO){
        try {
            positionService.positionSaleControllExport(response, positionSalesControVO);
        } catch (Exception e) {
            logger.error("点位导出异常" + e.getMessage());
        }
    }

    @RequestMapping(value="downloadFile",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "下载错误数据", httpMethod = "POST", value = "下载错误数据", position = 11, produces = MediaType.APPLICATION_JSON_VALUE)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestBody PositionSalesControVO positionSalesControVO){
        try {
            positionService.downFile(response, positionSalesControVO.getFileUrl());
        } catch (Exception e) {
            logger.error("下载数据异常" + e.getMessage());
        }
    }

    @RequestMapping(value="salesConDetail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "点位销控详情", httpMethod = "POST", value = "点位销控详情", position = 12, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData salesConDetail(HttpServletRequest request, @RequestBody PositionSalesControVO positionSalesControVO){
        RespData respData = null;
        try {
            LoginUser loginInfo = getLoginInfo(request);
            respData = positionService.salescontroDetail(positionSalesControVO, loginInfo.getOriginId());
        } catch (Exception e) {
            logger.error("salesConDetail失败{}" + e.getMessage());
            return RespData.fail("查看销控图详情失败!");
        }

        return respData;
    }

    @RequestMapping(value="positionDetail",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(notes = "销控图查看点位详情", httpMethod = "POST", value = "销控图查看点位详情", position = 13, produces = MediaType.APPLICATION_JSON_VALUE)
    public RespData getPositionDetail(HttpServletRequest request, @RequestBody PositionSalesControVO positionSalesControVO){
        RespData positionDetail = null;
        try {
            positionDetail = positionService.getPositionDetail(positionSalesControVO.getPositionCode());
        } catch (Exception e) {
            logger.error("getPositionDetail失败{}" + e.getMessage());
            return RespData.fail("查看销控 点位详情失败!");
        }

        return positionDetail;
    }
}
