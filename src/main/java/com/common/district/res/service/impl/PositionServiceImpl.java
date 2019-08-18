package com.common.district.res.service.impl;

import com.common.district.ad.dao.ContractDetailMapper;
import com.common.district.ad.dao.UploadLogMapper;
import com.common.district.ad.model.UploadLog;
import com.common.district.common.Constant;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.constants.CodeTypeEnum;
import com.common.district.common.excel.ExportExcel;
import com.common.district.common.utils.AFtpUtils;
import com.common.district.common.utils.CodeGeneratorUtils;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.dao.DepartmentMapper;
import com.common.district.org.model.Department;
import com.common.district.org.model.LoginUser;
import com.common.district.res.dao.*;
import com.common.district.res.enums.ImportLogNoEnum;
import com.common.district.res.model.*;
import com.common.district.res.service.PositionService;
import com.common.district.res.util.ExcleUtils;
import com.common.district.res.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.HttpStatus;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private LocationInfoMapper locationInfoMapper;

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    @Autowired
    private UploadLogMapper uploadLogMapper;

    @Autowired
    private ContractDetailMapper contractDetailMapper;

    @Override
    public PageInfo<PositionVO> selectPageList(PositionVO positionVO) {
        Page<?> page = null;
        if (!positionVO.isPaging()) {
            Integer pageSize = (null == positionVO.getPageSize() || positionVO.getPageNum() <= 0)
                    ? Constant.DEFAULT_PAGE_SIZE : positionVO.getPageSize();
            Integer pageNum = (null == positionVO.getPageNum() || positionVO.getPageNum() <= 0) ? 1
                    : positionVO.getPageNum();
            // 限制查询最大条数
            if (Constant.MAX_PAGE_SIZE <= pageSize) {
                positionVO.setPageSize(Constant.MAX_PAGE_SIZE);
            }
            page = PageHelper.startPage(pageNum, pageSize);
        }
        List<PositionVO> positionList = positionMapper.selectPageList(positionVO);
        return new PageInfo<PositionVO>(null == page ? positionList.size() : page.getTotal(), positionList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespData positionSave(Position position) {
        PositionVO positionVO = new PositionVO();
        BeanUtils.copyProperties(position, positionVO);
        // 租户id
        positionVO.setOrganId("");
        List<Position> positionList = positionMapper.getPosition(positionVO);
        if (positionList.size() > 0) {
            return RespData.fail("此条数据已存在！");
        }

        if (position.getId() != null) {
            Position model = positionMapper.selectByPrimaryKey(position.getId());
            if (!model.getCompanyName().equals(position.getCompanyName()) || !model.getProjectName().equals(position.getProjectName())
                    || !model.getAreaName().equals(position.getAreaName()) || !model.getPositionName().equals(position.getPositionName())) {
                // 若项目、片区、项目、点位名称任意一个有修改则重新生成点位编码
                String company = StringUtils.getPYIndexStr(position.getCompanyName(), true);
                String area = StringUtils.getPYIndexStr(position.getAreaName(), true);
                String project = StringUtils.getPYIndexStr(position.getProjectName(), true);
                String positionName = StringUtils.getPYIndexStr(position.getPositionName(), true);
                String key = company.concat("-").concat(area).concat("-").concat(project).concat("-").concat(positionName);
                String num = CodeGeneratorUtils.getNextNumber(CodeTypeEnum.POSITION.getCode());
                position.setPositionCode(key.concat("-").concat(num));
            }
            // 编辑
            position.setUpdateTime(new Date());
            int count = positionMapper.updateByPrimaryKeySelective(position);
            if (count < 1) {
                return RespData.fail("保存失败！");
            }
        } else {
            // 新增
            String company = StringUtils.getPYIndexStr(position.getCompanyName(), true);
            String area = StringUtils.getPYIndexStr(position.getAreaName(), true);
            String project = StringUtils.getPYIndexStr(position.getProjectName(), true);
            String positionName = StringUtils.getPYIndexStr(position.getPositionName(), true);
            String key = company.concat("-").concat(area).concat("-").concat(project).concat("-").concat(positionName);
            String num = CodeGeneratorUtils.getNextNumber(Constant.POSITION_CODE_KEY);
            position.setPositionCode(key.concat("-").concat(num));
            position.setCreateTime(new Date());
            int count = positionMapper.insertSelective(position);
            if (count < 1) {
                return RespData.fail("保存失败！");
            }
        }
        return RespData.success("保存成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespData positionImport(String fileName, LoginUser loginUser, HttpServletRequest request) {
        try {
            //文件上传时已做限制，这里只需截取字符串，不做其余判断
            String[] split = fileName.split(",");
            String fileUrl = split[0];
            String oldFileName = split[1];
            String newFileName = split[2];
            URL url = new URL(split[0]);
            InputStream inputStream = url.openStream();
            Workbook workBook = null;
            if (newFileName.endsWith(".xls")) {
                workBook = new HSSFWorkbook(inputStream);
            } else if (newFileName.endsWith(".xlsx")) {
                workBook = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = workBook.getSheetAt(0);
            // 获得数据的总行数
            int totalRowNum = sheet.getLastRowNum();

            if (totalRowNum < 2) {
                return RespData.fail("请按照模板导入并填写楼盘信息");
            } else if (totalRowNum > 1000) {
                return RespData.fail("数据量过大请分批次导入");
            }
            // 保存错误信息
            Map<String, String> map = new HashMap<>();
            // 地区公司名称
            String companyName = "";
            // 片区名称
            String priceAreaName = "";
            // 项目名称
            String projectName = "";
            // 分期名称
            String partName = "";
            // 点位名称
            String positionName = "";
            // 点位编号
            String positionCode = "";
            // 一级资源名称
            String firstResourceName = "";
            // 二级资源名称
            String secondResourceName = "";
            // 点位位置
            String positionAddress = "";
            // 楼栋名称
            String buildingName = "";
            // 单元名称
            String unitName = "";
            // 电梯号
            String elevatorNum = "";
            // 房间号
            String roomNum = "";

            // 数据集合
            List<Position> positions = Lists.newArrayList();
            // 获得所有数据
            for (int i = 2; i <= totalRowNum; i++) {
                Row row = sheet.getRow(i);
                // 填充地区公司名称
                Cell cell = row.getCell(0);
                if (cell != null) {
                    companyName = cell.getStringCellValue().trim();
                }

                // 填充片区名称
                cell = row.getCell(1);
                if (cell != null) {
                    priceAreaName = cell.getStringCellValue().trim();
                }

                // 填充项目名称
                cell = row.getCell(2);
                if (cell != null) {
                    projectName = cell.getStringCellValue().trim();
                }

                // 填充分期名称
                cell = row.getCell(3);
                if (cell != null) {
                    partName = cell.getStringCellValue().trim();

                }

                // 填充点位名称
                cell = row.getCell(4);
                if (cell != null) {
                    positionName = cell.getStringCellValue().trim();
                }

                // 填充点位编号
                cell = row.getCell(5);
                if (cell != null) {
                    if (Cell.CELL_TYPE_STRING != cell.getCellType()) {
                        positionCode = (int) cell.getNumericCellValue() + "";
                    } else {
                        positionCode = cell.getStringCellValue().trim();
                    }
                }

                // 填充一级资源名称
                cell = row.getCell(6);
                if (cell != null) {
                    firstResourceName = cell.getStringCellValue();
                }

                // 填充二级资源名称
                cell = row.getCell(7);
                if (cell != null) {
                    secondResourceName = cell.getStringCellValue();
                }

                // 填充点位位置
                cell = row.getCell(8);
                if (cell != null) {
                    positionAddress = cell.getStringCellValue();
                }

                // 填充楼栋名称
                cell = row.getCell(9);
                if (cell != null) {
                    if (Cell.CELL_TYPE_STRING != cell.getCellType()) {
                        buildingName = (int) cell.getNumericCellValue() + "";
                    } else {
                        buildingName = cell.getStringCellValue().trim();
                    }
                }

                // 填充单元名称
                cell = row.getCell(10);
                if (cell != null) {
                    if (Cell.CELL_TYPE_STRING != cell.getCellType()) {
                        unitName = (int) cell.getNumericCellValue() + "";
                    } else {
                        unitName = cell.getStringCellValue().trim();
                    }
                }

                // 填充电梯号
                cell = row.getCell(11);
                if (cell != null) {
                    if (Cell.CELL_TYPE_STRING != cell.getCellType()) {
                        elevatorNum = (int) cell.getNumericCellValue() + "";
                    } else {
                        elevatorNum = cell.getStringCellValue().trim();
                    }
                }

                // 填充房间号
                cell = row.getCell(12);
                if (cell != null) {
                    if (Cell.CELL_TYPE_STRING != cell.getCellType()) {
                        roomNum = (int) cell.getNumericCellValue() + "";
                    } else {
                        roomNum = cell.getStringCellValue().trim();
                    }
                }
                //判断必填字段是否为空
                if (StringUtils.isBlank(companyName, priceAreaName, projectName, partName, positionName, positionCode, firstResourceName, secondResourceName)) {
                    int num = i + 1;
                    String remake = "必填字段为空";
                    map.put("第" + num + "行", remake);
                    continue;
                }
                //判断点位是否已存在
                Position position = positionMapper.selectByNameAndOrganId(positionCode, loginUser.getOriginId());
                if (position != null) {
                    int num = i + 1;
                    String remake = "点位已存在";
                    map.put("第" + num + "行", remake);
                    continue;
                }

                Company company = companyMapper.selectByNameAndOrganId(companyName, loginUser.getOriginId());
                if (company == null) {
                    int num = i + 1;
                    String remake = "地区公司不存在";
                    map.put("第" + num + "行", remake);
                    continue;
                }
                String companyId = company.getCompanyId();

                District district = districtMapper.selectByNameAndOrganId(priceAreaName, loginUser.getOriginId());
                if (district == null) {
                    int num = i + 1;
                    String remake = "片区不存在";
                    map.put("第" + num + "行", remake);
                    continue;
                }
                String regionId = district.getRegionId();

                Project project = projectMapper.selectByNameAndOrganId(projectName, loginUser.getOriginId());
                if (project == null) {
                    int num = i + 1;
                    String remake = "项目不存在";
                    map.put("第" + num + "行", remake);
                    continue;
                }
                String projectId = project.getProjectId();

                LocationInfo partInfo = locationInfoMapper.selectByNameAndOrganId(partName, loginUser.getOriginId(), projectId, 1);
                if (partInfo == null) {
                    int num = i + 1;
                    String remake = "分期不存在";
                    map.put("第" + num + "行", remake);
                    continue;
                }
                String locationId = partInfo.getLocationId();

                ResourceType resourceType = resourceTypeMapper.selectByName(loginUser.getOriginId(), firstResourceName);
                if (resourceType == null) {
                    int num = i + 1;
                    String remake = "一级资源不存在";
                    map.put("第" + num + "行", remake);
                    continue;
                }
                String firstResId = resourceType.getId();

                ResourceType secondResType = resourceTypeMapper.selectSecondResByName(loginUser.getOriginId(), secondResourceName, firstResId);
                if (secondResType == null) {
                    int num = i + 1;
                    String remake = "二级资源不存在";
                    map.put("第" + num + "行", remake);
                    continue;
                }
                String secondResId = secondResType.getId();

                Position positionNew = new Position();

                //判断楼栋
                if (StringUtils.isNotBlank(buildingName)) {
                    LocationInfo buildInfo = locationInfoMapper.selectByNameAndOrganId(partName, loginUser.getOriginId(), projectId, 2);

                    if (buildInfo == null) {
                        int num = i + 1;
                        String remake = "楼栋不存在";
                        map.put("第" + num + "行", remake);
                        continue;
                    }
                    positionNew.setBuildingId(buildInfo.getLocationId());
                    positionNew.setBuildingName(buildingName);
                }
                //判断单元
                if (StringUtils.isNotBlank(unitName)) {
                    LocationInfo unitInfo = locationInfoMapper.selectByNameAndOrganId(unitName, loginUser.getOriginId(), projectId, 3);

                    if (unitInfo == null) {
                        int num = i + 1;
                        String remake = "单元不存在";
                        map.put("第" + num + "行", remake);
                        continue;
                    }
                    positionNew.setUnitId(unitInfo.getLocationId());
                    positionNew.setUnitName(unitName);
                }

                // 判断电梯号
                if (StringUtils.isNotBlank(elevatorNum)) {
                    LocationInfo elevatorInfo = locationInfoMapper.selectByNameAndOrganId(elevatorNum, loginUser.getOriginId(), projectId, 4);

                    if (elevatorInfo == null) {
                        int num = i + 1;
                        String remake = "电梯不存在";
                        map.put("第" + num + "行", remake);
                        continue;
                    }
                    positionNew.setElevatorNum(elevatorNum);
                }

                // 判断房间号
                if (StringUtils.isNotBlank(roomNum)) {
                    LocationInfo roomInfo = locationInfoMapper.selectByNameAndOrganId(roomNum, loginUser.getOriginId(), projectId, 5);

                    if (roomInfo == null) {
                        int num = i + 1;
                        String remake = "房间号不存在";
                        map.put("第" + num + "行", remake);
                        continue;
                    }
                    positionNew.setRoomId(roomInfo.getLocationId());
                    positionNew.setRoomNum(roomNum);
                }

                positionNew.setPositionName(positionName);
                positionNew.setPositionCode(positionCode);
                positionNew.setFirstResourceType(firstResId);
                positionNew.setSecondResourceType(secondResId);
                positionNew.setCompanyId(companyId);
                positionNew.setCompanyName(companyName);
                positionNew.setAreaId(regionId);
                positionNew.setAreaName(partName);
                positionNew.setProjectId(projectId);
                positionNew.setProjectName(projectName);
                positionNew.setPartId(locationId);
                positionNew.setPartName(partName);
                positionNew.setPositionAddress(positionAddress);

                positionNew.setCreateTime(new Date());
                positionNew.setUpdateTime(new Date());
                positionNew.setCreateUserName(loginUser.getUserName());
                positionNew.setUpdateUserName(loginUser.getUserName());
                positionNew.setCreateUserId(loginUser.getUserId());
                positionNew.setUpdateUserId(loginUser.getUserId());
                positionNew.setOrganId(loginUser.getOriginId());
                positionNew.setDel(0);

                positions.add(positionNew);
            }

            if (MapUtils.isEmpty(map)) {
                positionMapper.insertPositionList(positions);

                int logCount = uploadLogMapper.getLogCount(loginUser.getOriginId(), 1);

                String filesName = newFileName;
                UploadLog uploadLog = new UploadLog();
                uploadLog.setFileName(filesName);
                uploadLog.setOldFileName(oldFileName);
                uploadLog.setType(1);
                uploadLog.setSheetName(sheet.getSheetName());
                uploadLog.setCreateUserId(loginUser.getUserId());
                uploadLog.setCreateUserName(loginUser.getUserName());
                uploadLog.setCreateTime(new Date());
                uploadLog.setUpdateUserId(loginUser.getUserId());
                uploadLog.setUpdateUserName(loginUser.getUserName());
                uploadLog.setUpdateTime(new Date());
                uploadLog.setOrganId(loginUser.getOriginId());
                uploadLog.setStatus(1);
                uploadLog.setFileNo(ImportLogNoEnum.INSTANCE.getLogNo(logCount));
                uploadLog.setFileUrl(fileUrl);

                uploadLogMapper.insertSelective(uploadLog);

                return RespData.success("导入成功!");

            }
            String folderPath = request.getSession().getServletContext().getRealPath("/");
            // 自定义的文件名称
            String uploadFileName = "错误数据.txt";
            // 设置存放xslx文件的路径
            String uploadFilePath = folderPath + uploadFileName;
            // 转存文件到指定的路径
            File file = new File(uploadFilePath);
            writeStringToFile(uploadFilePath, map.toString());
            String httpUrl = AFtpUtils.uploadPosition(uploadFileName, uploadFilePath);
            // 将转存的文件删除
            if (StringUtils.isNotBlank(httpUrl)) {
                File localFile = new File(uploadFilePath);
                if (localFile.exists()) {
                    localFile.delete();
                }
            }

            return RespData.fail(httpUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespData.fail("导入文件格式错误！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespData deletePosition(Long id, String userId, String userName) {
        int num = positionMapper.getCountByPositionId(id);
        if (num > 0) {
            return RespData.fail("该点位正在使用中，删除失败!");
        }
        int count = positionMapper.deletePosition(id, userId, userName);
        if (count < 1) {
            return RespData.fail("删除失败！");
        }
        return RespData.success("删除成功！");
    }

    @Override
    public void positionExport(HttpServletResponse response, PositionVO positionVO) throws Exception {
        List<PositionExportVO> positionList = positionMapper.selectPositionList(positionVO);
        String fileName = "点位.xlsx";
        ExportExcel exportExcel = new ExportExcel(null, PositionExportVO.class, "点位");
        exportExcel.setDataList(positionList);
        exportExcel.write(response, fileName);
    }

    @Override
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fileName, String pathName) {
        try {
            InputStream is = this.getClass().getResourceAsStream(pathName);
            byte[] fileData = input2byte(is);
            downloadFile(response, request, fileName, fileData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<UploadLogVO> getPosLogList(UploadLogVO uploadLogVO) {
        Page<?> page = null;
        //查询
        if (!uploadLogVO.isPaging()) {
            Integer pageSize = (null == uploadLogVO.getPageSize() || uploadLogVO.getPageNum() <= 0)
                    ? Constant.DEFAULT_PAGE_SIZE : uploadLogVO.getPageSize();
            Integer pageNum = (null == uploadLogVO.getPageNum() || uploadLogVO.getPageNum() <= 0) ? 1
                    : uploadLogVO.getPageNum();

            // 限制查询最大条数
            if (Constant.MAX_PAGE_SIZE <= pageSize) {
                uploadLogVO.setPageSize(Constant.MAX_PAGE_SIZE);
            }
            page = PageHelper.startPage(pageNum, pageSize);
        }

        List<UploadLogVO> uploadLogVOS = uploadLogMapper.selectPageList(uploadLogVO);
        return new PageInfo<UploadLogVO>(null == page ? uploadLogVOS.size() : page.getTotal(), uploadLogVOS);
    }

    @Override
    public RespData uploadFile(HttpServletRequest request, LoginUser loginUser) {
        String httpUrl = "";
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> map = multipartRequest.getFileMap();
            // 设置文件保存的本地路径
            if (map.size() > 0) {
                for (String key : map.keySet()) {
                    CommonsMultipartFile file = (CommonsMultipartFile) map.get(key);
                    // 文件原名称
                    String fileName = file.getOriginalFilename().replaceAll(" ", "");
                    String[] split = null;
                    if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
                        split = fileName.split("\\.");
                    } else {
                        return RespData.fail("该文件不是execel文件!");
                    }
                    String folderPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String uploadFileName = split[0] + "_" + loginUser.getUserName() + "_" + System.currentTimeMillis() + "." + split[1];
                    // 设置存放xslx文件的路径
                    String uploadFilePath = folderPath + uploadFileName;
                    // 转存文件到指定的路径
                    file.transferTo(new File(uploadFilePath));
                    httpUrl = AFtpUtils.uploadPosition(uploadFileName, uploadFilePath);
                    // 将转存的文件删除
                    if (StringUtils.isNotBlank(httpUrl)) {
                        File localFile = new File(uploadFilePath);
                        if (localFile.exists()) {
                            localFile.delete();
                        }
                    }
                    httpUrl = httpUrl + "," + fileName + "," + uploadFileName;
                }
            }
            return RespData.success(httpUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespData.fail("上传文件失败!");
    }

    @Override
    public PageInfo<PositionVO> getFreePositionList(ContractPositionVO positionSearchVo) {
        Page<?> page = null;
        try {
            if(StringUtils.isNotEmpty(positionSearchVo.getEndTime())){
                positionSearchVo.setEndTime(positionSearchVo.getEndTime().replace("00:00:00","23:59:59"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!positionSearchVo.isPaging()) {
            Integer pageSize = (null == positionSearchVo.getPageSize() || positionSearchVo.getPageNum() <= 0)
                    ? Constant.DEFAULT_PAGE_SIZE : positionSearchVo.getPageSize();
            Integer pageNum = (null == positionSearchVo.getPageNum() || positionSearchVo.getPageNum() <= 0) ? 1
                    : positionSearchVo.getPageNum();
            // 限制查询最大条数
            if (Constant.MAX_PAGE_SIZE <= pageSize) {
                positionSearchVo.setPageSize(Constant.MAX_PAGE_SIZE);
            }
            page = PageHelper.startPage(pageNum, pageSize);
        }
        List<PositionVO> positionList = positionMapper.getFreePositionList(positionSearchVo);
        return new PageInfo<PositionVO>(null == page ? positionList.size() : page.getTotal(), positionList);
    }

	@Override
    public PageInfo<PositionSalesControVO> selectSaleControlPageList(PositionSalesControVO positionSalesControVO) {
        Page<?> page = null;
        if (!positionSalesControVO.isPaging()) {
            Integer pageSize = (null == positionSalesControVO.getPageSize() || positionSalesControVO.getPageNum() <= 0)
                    ? Constant.DEFAULT_PAGE_SIZE : positionSalesControVO.getPageSize();
            Integer pageNum = (null == positionSalesControVO.getPageNum() || positionSalesControVO.getPageNum() <= 0) ? 1
                    : positionSalesControVO.getPageNum();
            // 限制查询最大条数
            if (Constant.MAX_PAGE_SIZE <= pageSize) {
                positionSalesControVO.setPageSize(Constant.MAX_PAGE_SIZE);
            }
            page = PageHelper.startPage(pageNum, pageSize);
        }
        List<PositionSalesControVO> positionSalesControVOS = projectMapper.selectPageList(positionSalesControVO);
        if (CollectionUtils.isNotEmpty(positionSalesControVOS)) {
            positionSalesControVOS.forEach(item -> {
                item.setSaledPositionCount(contractDetailMapper.getPositionCount(item.getProjectId(), 1));
                item.setUnSaledPositionCount(contractDetailMapper.getPositionCount(item.getProjectId(), 2));
            });
        }
        return new PageInfo<PositionSalesControVO>(null == page ? positionSalesControVOS.size() : page.getTotal(), positionSalesControVOS);
    }

    @Override
    public void positionSaleControllExport(HttpServletResponse response, PositionSalesControVO positionSalesControVO) throws Exception {
        List<PositionSalesControExportVO> positionSalesControExportVOS = projectMapper.selectPositionList(positionSalesControVO);
        if (CollectionUtils.isNotEmpty(positionSalesControExportVOS)) {
            positionSalesControExportVOS.forEach(item -> {
                item.setSaledPositionCount(contractDetailMapper.getPositionCount(item.getProjectId(), 1));
                item.setUnSaledPositionCount(contractDetailMapper.getPositionCount(item.getProjectId(), 2));
            });
        }
        String fileName = "点位销控.xlsx";
        ExportExcel exportExcel = new ExportExcel(null, PositionSalesControExportVO.class, "点位销控");
        exportExcel.setDataList(positionSalesControExportVOS);
        exportExcel.write(response, fileName);
    }

    @Override
    public void downFile(HttpServletResponse response, String fileUrl) throws Exception {
        //设置响应头控制浏览器以下载的形式打开文件
        response.setHeader("content-disposition", "attachment;fileName=" + "Student.xml");
        URL url = new URL(fileUrl);
        InputStream in = url.openStream();
        //获取下载文件的输入流
        int count = 0;
        byte[] by = new byte[1024];

        //通过response对象获取OutputStream流
        OutputStream out = response.getOutputStream();
        while ((count = in.read(by)) != -1) {
            out.write(by, 0, count);//将缓冲区的数据输出到浏览器
        }

        in.close();
        out.flush();
        out.close();

    }

    @Override
    public RespData salescontroDetail(PositionSalesControVO positionSalesControVO, String organId) throws Exception{
        PositionSalesControVO salesControVO = null;
        List<PositionSalesControVO> positionSalesControVOS = projectMapper.selectPageList(positionSalesControVO);
        Integer status = positionSalesControVO.getStatus();
        String resourceId = positionSalesControVO.getResourceId();
        if(CollectionUtils.isNotEmpty(positionSalesControVOS)){
            salesControVO = positionSalesControVOS.get(0);
            salesControVO.setSaledPositionCount(contractDetailMapper.getPositionCount(salesControVO.getProjectId(), 1));
            salesControVO.setUnSaledPositionCount(contractDetailMapper.getPositionCount(salesControVO.getProjectId(), 2));

            salesControVO.setPositionCount(salesControVO.getSaledPositionCount()+salesControVO.getUnSaledPositionCount());
            //根据分期查询出二级位置信息
            List<LocationInfoVo> lists = Lists.newArrayList();

            List<LocationInfoVo> scondInfoVos = locationInfoMapper.selectByParentId(salesControVO.getStageId());
            if(CollectionUtils.isNotEmpty(scondInfoVos)){
                scondInfoVos.forEach(scondItem->{
                    //查询三级位置信息
                    List<LocationInfoVo> thirdInfoVos = locationInfoMapper.selectByParentId(scondItem.getLocationId());
                    if(CollectionUtils.isNotEmpty(thirdInfoVos)){
                        thirdInfoVos.forEach(thirdItem->{
                            //查询四级位置信息
                            List<LocationInfoVo> fouthInfoVos = locationInfoMapper.selectByParentId(thirdItem.getLocationId());
                            if(CollectionUtils.isNotEmpty(fouthInfoVos)){
                                //查询五级位置信息
                                fouthInfoVos.forEach(fouthItem->{
                                    List<LocationInfoVo> fifthInfoVos = locationInfoMapper.selectByParentId(fouthItem.getLocationId());
                                    if(CollectionUtils.isNotEmpty(fifthInfoVos)){
                                        fifthInfoVos.forEach(fifthItem->{
                                            fifthItem.setName(scondItem.getLocationName()+"-"+thirdItem.getLocationName()+"-"+fouthItem.getLocationName()+"-"+fifthItem.getLocationName());
                                            fifthItem.setPositionIds(positionMapper.selectPositionCodes(fifthItem.getLocationId(),fifthItem.getLocationType(),status,resourceId));
                                            lists.add(fifthItem);
                                        });
                                    }else{
                                        fouthItem.setName(scondItem.getLocationName()+"-"+thirdItem.getLocationName()+"-"+fouthItem.getLocationName());
                                        fouthItem.setPositionIds(positionMapper.selectPositionCodes(fouthItem.getLocationId(),fouthItem.getLocationType(),status,resourceId));
                                        lists.add(fouthItem);
                                    }
                                });
                            }else{
                                thirdItem.setName(scondItem.getLocationName()+"-"+thirdItem.getLocationName());
                                thirdItem.setPositionIds(positionMapper.selectPositionCodes(thirdItem.getLocationId(),thirdItem.getLocationType(),status,resourceId));
                                lists.add(thirdItem);
                            }
                        });
                    }else{
                        scondItem.setName(scondItem.getLocationName());
                        scondItem.setPositionIds(positionMapper.selectPositionCodes(scondItem.getLocationId(),scondItem.getLocationType(),status,resourceId));
                        lists.add(scondItem);
                    }

                });
            }
            salesControVO.setPositions(lists);
        }
        if(salesControVO == null){
           return RespData.fail("点位详情查询失败");
        }
        return RespData.success(salesControVO);
    }

    @Override
    public RespData getPositionDetail(String positionCode) throws Exception{
        PositionVO positionVO = null;
        Position position = positionMapper.selectByPositionCode(positionCode);

        if(position!=null){
            positionVO = new PositionVO();
            positionVO.setId(position.getId());
            positionVO.setPositionName(position.getPositionName());
            positionVO.setPositionCode(position.getPositionCode());
            positionVO.setFirstResourceType(position.getFirstResourceType());
            positionVO.setSecondResourceType(position.getSecondResourceType());
            positionVO.setThirdResourceType(position.getThirdResourceType());
            positionVO.setHealthDegree(position.getHealthDegree());
            positionVO.setReferencePrice(position.getReferencePrice());
            positionVO.setProjectId(position.getProjectId());
            positionVO.setPartName(position.getPartName());
            positionVO.setBuildingName(position.getBuildingName());
            positionVO.setUnitName(position.getUnitName());
            positionVO.setElevatorNum(position.getElevatorNum());
            positionVO.setRoomNum(position.getRoomNum());
            positionVO.setPositionAddress(position.getPositionAddress());
            positionVO.setCompanyName(position.getCompanyName());
            positionVO.setAreaName(position.getAreaName());
            positionVO.setProjectName(position.getProjectName());
            if(StringUtils.isNotBlank(positionVO.getFirstResourceType())){
                positionVO.setFirstResourceTypeName(resourceTypeMapper.selectResourceNameById(positionVO.getFirstResourceType()));
            }
            if(StringUtils.isNotBlank(positionVO.getSecondResourceType())){
                positionVO.setSecondResourceTypeName(resourceTypeMapper.selectResourceNameById(positionVO.getSecondResourceType()));

            }
            if(StringUtils.isNotBlank(positionVO.getThirdResourceType())){
                positionVO.setThirdResourceTypeName(resourceTypeMapper.selectResourceNameById(positionVO.getThirdResourceType()));

            }
            positionVO.setContId(positionVO.getProjectId() == null ? null : positionMapper.selectContIdByprojectId(positionVO.getProjectId()));

        }
        if(positionVO!=null){
            return RespData.success(positionVO);
        }
        return RespData.fail("查询不到点位信息！");
    }

    /**
     * inputstream转Byte[]
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    private byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * 下载
     *
     * @param response
     * @param request
     * @param filename
     * @param fileData
     * @return
     */
    private boolean downloadFile(HttpServletResponse response,
                                 HttpServletRequest request, String filename, byte[] fileData) {
        try {
            OutputStream myout = null;
            // 检查时候获取到数据
            if (fileData == null) {
                response.sendError(HttpStatus.SC_NOT_FOUND);
                return false;
            }
            try {
                if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                    filename = new String(filename.getBytes("GBK"), "iso-8859-1");
                } else {
                    filename = URLEncoder.encode(filename, "utf-8");
                }
                response.setContentType("multipart/form-data");
                /*response.setContentType("multipart/form-data;charset=utf-8");*/
                response.setHeader("content-disposition", "attachment;filename=" + filename);
                // 写明要下载的文件的大小
                response.setContentLength(fileData.length);
                // 从response对象中得到输出流,准备下载
                myout = response.getOutputStream();
                myout.write(fileData);
                myout.flush();
            } catch (Exception e) {
            } finally {
                if (myout != null) {
                    try {
                        myout.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private void writeStringToFile(String filePath, String errData) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            writer.write(errData);

            writer.flush();
            writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
