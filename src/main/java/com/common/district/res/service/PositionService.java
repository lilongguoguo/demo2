package com.common.district.res.service;

import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.org.model.LoginUser;
import com.common.district.res.controller.PositionController;
import com.common.district.res.model.Position;
import com.common.district.res.vo.LocationInfoVo;
import com.common.district.res.vo.PositionSalesControVO;
import com.common.district.res.vo.ContractPositionVO;
import com.common.district.res.vo.PositionVO;
import com.common.district.res.vo.UploadLogVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface PositionService {
    /**
     * 列表查询
     * @param positionVO
     * @return
     */
    PageInfo<PositionVO> selectPageList(PositionVO positionVO);

    /**
     * 保存
     * @param position
     * @return
     */
    RespData positionSave(Position position);

    /**
     * 点位导入
     * @param fileName
     * @return
     */
    RespData positionImport(String fileName, LoginUser loginUser ,HttpServletRequest request);

    /**
     * 点位删除
     * @param id
     * @return
     */
    RespData deletePosition(Long id, String userId, String userName);

    /**
     * 点位导出
     * @param response
     * @param positionVO
     * @throws Exception
     */
    void positionExport(HttpServletResponse response, PositionVO positionVO) throws Exception;

    /**
     * 模板下载
     * @param request
     * @param response
     * @param fileName
     * @param pathName
     * @throws IOException
     */
    void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fileName, String pathName);

    /**
     * 导入列表查询
     * @return
     */
    PageInfo<UploadLogVO>getPosLogList(UploadLogVO uploadLogVO);

    /**
     * 文件上传
     * @param request
     * @return
     * @throws IOException
     */
    RespData uploadFile(HttpServletRequest request,LoginUser loginUser);

    /**
     * (合同使用) 此接口使用于合同选择空闲点位和查看合同已选点位）
     * @param positionSearchVo
     * @return
     */
    PageInfo<PositionVO> getFreePositionList(ContractPositionVO positionSearchVo);
	
	/**
     *
     * @param positionSalesControVO
     * @return
     */
    PageInfo<PositionSalesControVO> selectSaleControlPageList(PositionSalesControVO positionSalesControVO);

    /**
     * 点位销控导出
     * @param response
     * @param positionSalesControVO
     * @throws Exception
     */
    void positionSaleControllExport(HttpServletResponse response, PositionSalesControVO positionSalesControVO) throws Exception;

    /**
     * 下载错误数据
     */
    void downFile (HttpServletResponse response, String fileUrl) throws Exception;

    /**
     * 点位销控详情
     */

    RespData salescontroDetail(PositionSalesControVO positionSalesControVO, String organId) throws Exception;

    /**
     * 销控 -- 点位详情
     */
    RespData getPositionDetail(String positionCode)throws Exception;
}
