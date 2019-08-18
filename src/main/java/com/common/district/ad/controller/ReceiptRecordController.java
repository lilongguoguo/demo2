package com.common.district.ad.controller;

import com.common.district.ad.model.ReceiptRecord;
import com.common.district.ad.model.ReceivablesBill;
import com.common.district.ad.service.ReceiptRecordService;
import com.common.district.ad.vo.ReceiptRecordVo;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.controller.BaseController;
import com.common.district.res.model.Company;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/receipt" , method = RequestMethod.POST)
@Api(description = "收款记录" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceiptRecordController extends BaseController {

    @Autowired
    private ReceiptRecordService receiptRecordService;
    @RequestMapping(value = "companyList")
    @ApiOperation(value="获取公司下拉列表" , position = 1)
    public RespData<List<Company>> getCompanyList(){
        String organId = "123456";
        return RespData.success(receiptRecordService.getCompanyListByOrganId(organId));
    }



    @RequestMapping(value="list")
    @ApiOperation(value="收款记录管理列表" , position = 2)
    public RespData<PageInfo<ReceiptRecord>> getReceiptList(@RequestBody ReceiptRecordVo receVo){
        PageInfo<ReceiptRecord> pageInfo = null;
        try{
            //租户Id暂时为空
            receVo.setOrganId("123456");
            pageInfo =receiptRecordService.getReceiptList(receVo);
        }catch(Exception e){
            e.printStackTrace();
            logger.info("获取收款记录管理列表接口失败{}" , e.getMessage());
            return RespData.fail("获取收款记录管理列表接口失败");
        }
        return RespData.success(pageInfo);
    }


    @RequestMapping(value = "checkDetail")
    @ApiOperation(value="查看收款详情" , position = 3)
    public RespData checkDetail(Long rrId){
        Map map = new HashMap();
        try{
            //应收账单列表
            List<ReceivablesBill> bills = receiptRecordService.getReceiptBillList(rrId);
            //收款信息 & 发票信息
            ReceiptRecord receiptRecord = receiptRecordService.getReceiptById(rrId);

            map.put("bills" , bills);
            map.put("receipt" , receiptRecord);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查看收款详情接口失败{}" , e.getMessage());
            return RespData.fail("查看收款详情接口失败");
        }

        return RespData.success(map);
    }






}
