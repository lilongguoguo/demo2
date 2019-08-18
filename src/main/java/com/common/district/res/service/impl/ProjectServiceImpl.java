package com.common.district.res.service.impl;

import com.common.district.common.Constant;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.constants.CodeTypeEnum;
import com.common.district.common.constants.ConstantsEnum;
import com.common.district.common.excel.ExportExcel;
import com.common.district.common.utils.CodeGeneratorUtils;
import com.common.district.common.utils.IdCreateUtil;
import com.common.district.common.utils.StringUtils;
import com.common.district.org.model.LoginUser;
import com.common.district.res.dao.*;
import com.common.district.res.enums.BusinessTypeEnum;
import com.common.district.res.model.*;
import com.common.district.res.service.ProjectService;
import com.common.district.res.vo.PositionExportVO;
import com.common.district.res.vo.PositionVO;
import com.common.district.res.vo.ProjectVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private LocationInfoMapper locationInfoMapper;

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Project> getProjectListByOrganIdAndConLst(List<String> companyList, String organId) {
        return projectMapper.getProjectListByOrganIdAndConLst(companyList,organId);
    }

    @Override
    public PageInfo<ProjectVo> selectPageList(ProjectVo projectVo) {
        Page<?> page = null;
        if (!projectVo.isPaging()) {
            Integer pageSize = (null == projectVo.getPageSize() || projectVo.getPageNum() <= 0)
                    ? Constant.DEFAULT_PAGE_SIZE : projectVo.getPageSize();
            Integer pageNum = (null == projectVo.getPageNum() || projectVo.getPageNum() <= 0) ? 1
                    : projectVo.getPageNum();
            // 限制查询最大条数
            if (Constant.MAX_PAGE_SIZE <= pageSize) {
                projectVo.setPageSize(Constant.MAX_PAGE_SIZE);
            }
            page = PageHelper.startPage(pageNum, pageSize);
        }
        List<ProjectVo> projectList = projectMapper.selectProjectList(projectVo);
        if(CollectionUtils.isNotEmpty(projectList)){
            projectList.forEach(item->{
                if(StringUtils.isNotBlank(item.getBusinessType())){
                    int integer = Integer.valueOf(item.getBusinessType());
                    item.setBusinessName(BusinessTypeEnum.getVla(integer));
                }
            });
        }
        return new PageInfo<ProjectVo>(null == page ? projectList.size() : page.getTotal(), projectList);
    }

    @Override
    public void projectExport(HttpServletResponse response, ProjectVo projectVo) throws Exception {
        List<ProjectVo> projectList = projectMapper.selectProjectList(projectVo);

        // 根据业态类型Integer 获取业态名称
        if(CollectionUtils.isNotEmpty(projectList)){
            projectList.forEach(item->{
                if(StringUtils.isNotBlank(item.getBusinessType())){
                    int integer = Integer.valueOf(item.getBusinessType());
                    item.setBusinessName(BusinessTypeEnum.getVla(integer));
                }
            });
        }

        String fileName = "项目.xlsx";
        ExportExcel exportExcel = new ExportExcel(null, ProjectVo.class, "项目");
        exportExcel.setDataList(projectList);
        exportExcel.write(response, fileName);
    }

    @Override

    public RespData deleteProject(LoginUser loginUser, String projectId) throws Exception{
        int count = positionMapper.getPositionCountByProjectId(projectId, loginUser.getOriginId());

        if(count > 0){
            return RespData.fail("该项目存在点位信息无法删除");
        }
        //删除该项目的位置信息
        List<LocationInfo> locationInfos = locationInfoMapper.selectByProjectId(loginUser.getOriginId(), projectId);
        if(CollectionUtils.isNotEmpty(locationInfos)){
            locationInfos.forEach(item->{
                item.setDel(1);
                item.setUpdateTime(new Date());
                item.setUpdateUserId(loginUser.getUserId());
                locationInfoMapper.updateByPrimaryKeySelective(item);
            });
        }

        //删除该项目
        Project project = projectMapper.selectByPrimaryKey(projectId);
        project.setDel(1);
        project.setUpdateTime(new Date());
        project.setUpdateUserId(loginUser.getUserId());
        int i = projectMapper.updateByPrimaryKeySelective(project);
        if(i > 0){
            return RespData.success("删除成功");
        }
        return RespData.fail("删除失败");
    }

    /*@Override
    public boolean checkProject(String organId, String projectName) throws Exception {
        int i = projectMapper.checkProject(organId, projectName);
        if(i == 0){
            return true;
        }
        return false;
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespData saveProject(Project project) throws Exception {
        if (StringUtils.isNotBlank(project.getProjectId())) {
            Project proModel = projectMapper.selectByPrimaryKey(project.getProjectId());
            if (StringUtils.equals(proModel.getProjectName(),project.getProjectName())) {
                // 若项目名称改变 则项目简称 全称都要改变
                String projectCode = StringUtils.getPYIndexStr(project.getProjectName(), true);
                project.setProjectCode(projectCode);
                project.setProjectShortName(project.getProjectName());
                project.setProjectFullName(project.getProjectName());
            }
            if (StringUtils.equals(proModel.getCompanyId(),project.getCompanyId())) {
                // 若公司改变 则公司code 名称都要改变
                Company company = companyMapper.selectByPrimaryKey(project.getCompanyId());
                project.setCompanyCode(company.getCompanyShortName());
                project.setCompanyName(company.getCompanyName());
            }
            // 编辑
            int count = projectMapper.updateByPrimaryKeySelective(project);
            if (count < 1) {
                return RespData.fail("保存失败!");
            }
        } else {
            int i = projectMapper.checkProject(project.getOrganId(), project.getProjectName());
            if(i > 0){
                return RespData.fail("项目已存在");
            }
            // 新增
            Company company = companyMapper.selectByPrimaryKey(project.getCompanyId());
            District district = districtMapper.selectByPrimaryKey(project.getRegionId());
            project.setRegionName(district.getRegionName());
            String projectCode = StringUtils.getPYIndexStr(project.getProjectName(), true);
            project.setProjectCode(projectCode);
            project.setCompanyCode(company.getCompanyShortName());
            project.setCompanyName(company.getCompanyName());
            //项目全拼 简拼规则尚未定义 视为无用字段暂定为项目名称
            project.setProjectShortName(project.getProjectName());
            project.setProjectFullName(project.getProjectName());
            project.setProjectId(IdCreateUtil.create(ConstantsEnum.IdCreatePreEnum.PROJECT));
            int count = projectMapper.insertSelective(project);
            if (count < 1) {
                return RespData.fail("保存失败！");
            }
            // 保存成功同时新增分期
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLocationId(IdCreateUtil.create(ConstantsEnum.IdCreatePreEnum.LOCATIONINFO));
            locationInfo.setLocationName(project.getProjectName());
            locationInfo.setLocationType(1);
            locationInfo.setProjectId(project.getProjectId());
            locationInfo.setOrganId(project.getOrganId());
            locationInfo.setCreateUserId(project.getCreateUserId());
            locationInfo.setCreateTime(new Date());
            locationInfo.setDel(0);
            locationInfoMapper.insertSelective(locationInfo);
        }

        return RespData.success("保存成功！");
    }

    @Override
    public RespData getProjectDetail(String projectId) throws Exception {
        ProjectVo projectVo = new ProjectVo();
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if(project != null){
            projectVo.setProjectId(project.getProjectId());
            projectVo.setProjectName(project.getProjectName());
            projectVo.setCompanyId(project.getCompanyId());
            projectVo.setCompanyName(project.getCompanyName());
            projectVo.setRegionId(project.getRegionId());
            projectVo.setRegionName(project.getRegionName());
            projectVo.setLatitude(project.getLatitude());
            projectVo.setLongitude(project.getLongitude());
            projectVo.setDesp(project.getDesp());
            projectVo.setRemark(project.getRemark());
            if(project.getBusinessType() != null){
                projectVo.setBusinessType(project.getBusinessType()+"");
                projectVo.setBusinessName(BusinessTypeEnum.getVla(project.getBusinessType()));
            }
            projectVo.setProjectCode(project.getProjectCode());
            if(StringUtils.isNotBlank(project.getProvinceId())){
                Region region = regionMapper.selectByPrimaryKey(project.getProvinceId());
                projectVo.setProvinceId(project.getProvinceId());
                projectVo.setProvinceName(region.getDictRegionName());
            }
            if(StringUtils.isNotBlank(project.getCityId())){
                Region region = regionMapper.selectByPrimaryKey(project.getCityId());
                projectVo.setCityId(project.getCityId());
                projectVo.setCityName(region.getDictRegionName());
            }
            if(StringUtils.isNotBlank(project.getAreaId())){
                Region region = regionMapper.selectByPrimaryKey(project.getAreaId());
                projectVo.setAreaId(project.getAreaId());
                projectVo.setAreaName(region.getDictRegionName());
            }
            return RespData.success(projectVo);
        }
        return RespData.fail("查询失败");
    }
}
