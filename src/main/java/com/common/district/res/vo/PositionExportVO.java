package com.common.district.res.vo;

import com.common.district.common.excel.annotation.ExcelField;

import java.math.BigDecimal;
import java.util.Date;

public class PositionExportVO {

    @ExcelField(title="点位名称", align=2, sort=1)
    private String positionName;

    @ExcelField(title="点位编号", align=2, sort=2)
    private String positionCode;

    @ExcelField(title="一级资源类型", align=2, sort=3)
    private String firstResourceTypeName;

    @ExcelField(title="二级资源类型", align=2, sort=4)
    private String secondResourceTypeName;

    @ExcelField(title="公司", align=2, sort=5)
    private String companyName;

    @ExcelField(title="片区", align=2, sort=6)
    private String areaName;

    @ExcelField(title="项目", align=2, sort=7)
    private String projectName;

    @ExcelField(title="组团/区", align=2, sort=8)
    private String partName;

    @ExcelField(title="楼栋", align=2, sort=9)
    private String buildingName;

    @ExcelField(title="单元", align=2, sort=10)
    private String unitName;

    @ExcelField(title="电梯", align=2, sort=11)
    private String elevatorNum;

    @ExcelField(title="点位位置", align=2, sort=12)
    private String positionAddress;

    @ExcelField(title="健康度", align=2, sort=13)
    private String healthDegreeName;

    @ExcelField(title="参考价格(元)", align=2, sort=14)
    private BigDecimal referencePrice;

    // 三级资源类型
    private String thirdResourceTypeName;
    // 容量
    private BigDecimal capacity;
    // 容量单位
    private Integer capacityUnitId;
    // 规格
    private BigDecimal spec;
    // 规格单位
    private Integer specUnitId;
    // 房间号
    private String roomNum;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getFirstResourceTypeName() {
        return firstResourceTypeName;
    }

    public void setFirstResourceTypeName(String firstResourceTypeName) {
        this.firstResourceTypeName = firstResourceTypeName;
    }

    public String getSecondResourceTypeName() {
        return secondResourceTypeName;
    }

    public void setSecondResourceTypeName(String secondResourceTypeName) {
        this.secondResourceTypeName = secondResourceTypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getElevatorNum() {
        return elevatorNum;
    }

    public void setElevatorNum(String elevatorNum) {
        this.elevatorNum = elevatorNum;
    }

    public String getPositionAddress() {
        return positionAddress;
    }

    public void setPositionAddress(String positionAddress) {
        this.positionAddress = positionAddress;
    }

    public String getHealthDegreeName() {
        return healthDegreeName;
    }

    public void setHealthDegreeName(String healthDegreeName) {
        this.healthDegreeName = healthDegreeName;
    }

    public BigDecimal getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(BigDecimal referencePrice) {
        this.referencePrice = referencePrice;
    }

    public String getThirdResourceTypeName() {
        return thirdResourceTypeName;
    }

    public void setThirdResourceTypeName(String thirdResourceTypeName) {
        this.thirdResourceTypeName = thirdResourceTypeName;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacityUnitId() {
        return capacityUnitId;
    }

    public void setCapacityUnitId(Integer capacityUnitId) {
        this.capacityUnitId = capacityUnitId;
    }

    public BigDecimal getSpec() {
        return spec;
    }

    public void setSpec(BigDecimal spec) {
        this.spec = spec;
    }

    public Integer getSpecUnitId() {
        return specUnitId;
    }

    public void setSpecUnitId(Integer specUnitId) {
        this.specUnitId = specUnitId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}