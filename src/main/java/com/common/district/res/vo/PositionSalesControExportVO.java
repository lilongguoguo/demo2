package com.common.district.res.vo;

import com.common.district.common.excel.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;

public class PositionSalesControExportVO implements Serializable {

    private static final long serialVersionUID = -5290185985415634L;

    /**
     * 公司名称
     */
    @ExcelField(title="公司", align=2, sort=1)
    private String companyName;
    /**
     * 片区名称
     */
    @ExcelField(title="片区", align=2, sort=2)
    private String regionName;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 项目名称
     */
    @ExcelField(title="项目", align=2, sort=3)
    private String projectName;
    /**
     * 分期名称
     */
    @ExcelField(title="项目", align=2, sort=4)
    private String stageName;
    /**
     * 已出租点位数量
     */
    @ExcelField(title="已出租点位数量", align=2, sort=5)
    private Integer saledPositionCount;
    /**
     * 未出租点位数量
     */
    @ExcelField(title="未出租点位数量", align=2, sort=6)
    private Integer unSaledPositionCount;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}