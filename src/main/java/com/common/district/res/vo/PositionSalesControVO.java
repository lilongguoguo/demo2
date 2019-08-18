package com.common.district.res.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PositionSalesControVO implements Serializable {

    private static final long serialVersionUID = 4346611165432212410L;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 片区id
     */
    private String regionId;
    /**
     * 片区名称
     */
    private String regionName;
    /**
     * 分期id
     */
    private String stageId;
    /**
     * 分期名称
     */
    private String stageName;
    /**
     * 已出租点位数量
     */
    private Integer saledPositionCount;
    /**
     * 未出租点位数量
     */
    private Integer unSaledPositionCount;
    /**
     * 共有点位数量
     */
    private Integer positionCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * fileUrl
     */
    private String fileUrl;
    /**
     * 租户id
     */
    private String organId;
    /**
     * 一级资源id
     */
    private String resourceId;
    /**
     * 点位状态
     */
    private Integer status;
    /**
     * 点位编号
     */
    private String positionCode;
    /**
     * 点位 位置信息
     */
    private List<LocationInfoVo> positions;
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

    public Integer getSaledPositionCount() {
        return saledPositionCount;
    }

    public void setSaledPositionCount(Integer saledPositionCount) {
        this.saledPositionCount = saledPositionCount;
    }

    public Integer getUnSaledPositionCount() {
        return unSaledPositionCount;
    }

    public void setUnSaledPositionCount(Integer unSaledPositionCount) {
        this.unSaledPositionCount = unSaledPositionCount;
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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public List<LocationInfoVo> getPositions() {
        return positions;
    }

    public void setPositions(List<LocationInfoVo> positions) {
        this.positions = positions;
    }

    public Integer getPositionCount() {
        return positionCount;
    }

    public void setPositionCount(Integer positionCount) {
        this.positionCount = positionCount;
    }
}