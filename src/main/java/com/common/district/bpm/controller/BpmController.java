package com.common.district.bpm.controller;

import com.common.district.bpm.service.BpmService;
import com.common.district.bpm.vo.ApproveParam;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "api/bpm")
@Api(description = "审批流通用接口" , produces = MediaType.APPLICATION_JSON_VALUE)
public class BpmController extends BaseController {
    @Autowired
    private BpmService bpmService;

    @RequestMapping("selectNoteList")
    @ApiOperation(value = "查询审批流对应节点（编辑）",httpMethod = "POST")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "relationId",value = "业务id，编辑的时候传",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "relationType",value = "业务类型", required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "templateCode",value = "模板编码", required = true,paramType = "query",dataType = "String")
    })
    public RespData selectNoteList( Long relationId, Integer relationType, String templateCode,
                                                           HttpServletRequest request){
        return bpmService.selectNoteList(relationId,relationType,templateCode,getLoginInfo(request));
    }
    @RequestMapping("selectNoteListR")
    @ApiOperation(value = "查询审批流对应节点（查看）",httpMethod = "POST")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "relationId",value = "业务id", required = true,paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "relationType",value = "业务类型", required = true,paramType = "query",dataType = "int")
    })
    public RespData selectNoteListR( Long relationId, Integer relationType){
        return bpmService.selectNoteListR(relationId,relationType);
    }

    @RequestMapping("selectButtonList")
    @ApiOperation(value = "查询审批按钮",httpMethod = "POST")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "relationId",value = "业务id,没有就不传",paramType = "query",dataType = "Long"),
            @ApiImplicitParam(name = "relationType",value = "业务类型", required = true,paramType = "query",dataType = "int")
    })
    public RespData selectButtonList(Long relationId, Integer relationType, HttpServletRequest request){
        return bpmService.selectButtonList(relationId,relationType,getLoginInfo(request));
    }

    @RequestMapping("selectRecordList")
    @ApiOperation(value = "查询审批记录",httpMethod = "POST")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "relationId",value = "业务id",required = true,paramType = "query",dataType = "Long"),
            @ApiImplicitParam(name = "relationType",value = "业务类型", required = true,paramType = "query",dataType = "int")
    })
    public RespData selectRecordList(Long relationId, Integer relationType){
        return bpmService.selectRecordList(relationId,relationType);
    }

    @RequestMapping("doApprove")
    @ApiOperation(value = "通用审批操作 正常审批不需要传notes字段信息", httpMethod = "POST")
    public RespData doApprove(HttpServletRequest request, @RequestBody ApproveParam approveParam){
        return bpmService.doApprove(approveParam,getLoginInfo(request));
    }

}
