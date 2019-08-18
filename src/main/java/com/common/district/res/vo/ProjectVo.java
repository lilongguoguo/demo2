package com.common.district.res.vo;

import com.common.district.common.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 11:45 2019/8/12
 */
public class ProjectVo implements Serializable {

    private static final long serialVersionUID = -2751049999505966047L;

    /**
     * 项目id
     */
    private String projectId;
    /**
     * 项目名称
     */
    @ExcelField(title="项目名称", align=2, sort=2)
    private String projectName;
    /**
     * 项目编码
     */
    @ExcelField(title="项目编号", align=2, sort=1)
    private String projectCode;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 公司名称
     */
    @ExcelField(title="公司", align=2, sort=3)
    private String companyName;
    /**
     * 片区id
     */
    private String regionId;
    /**
     * 片区名称
     */
    @ExcelField(title="片区", align=2, sort=4)
    private String regionName;
    /**
     * 业态
     */
    private String businessType;
    /**
     * 业态名称
     */
    @ExcelField(title="业态", align=2, sort=5)
    private String businessName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 租户id
     */
    private String organId;
    /**
     * 省id
     */
    private String provinceId;
    /**
     * 省
     */
    private String provinceName;
    /**
     * 市id
     */
    private String cityId;
    /**
     * 市
     */
    private String cityName;
    /**
     * 区id
     */
    private String areaId;
    /**
     * 区
     */
    private String areaName;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 维度
     */
    private String latitude;
    /**
     * 描述
     */
    private String desp;
    /**
     * 备注
     */
    private String remark;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 是否分页
     */
    private boolean paging;
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
