package com.common.district.res.model;

import java.math.BigDecimal;
import java.util.Date;

public class Position {
    private Long id;

    private String positionName;

    private String positionCode;

    private String firstResourceType;

    private String secondResourceType;

    private String thirdResourceType;

    private Integer healthDegree;

    private BigDecimal referencePrice;

    private BigDecimal capacity;

    private Integer capacityUnitId;

    private BigDecimal spec;

    private Integer specUnitId;

    private String companyId;

    private String companyName;

    private String areaId;

    private String areaName;

    private String projectId;

    private String projectName;

    private String partId;

    private String partName;

    private String buildingId;

    private String buildingName;

    private String unitId;

    private String unitName;

    private String elevatorNum;

    private String roomId;

    private String roomNum;

    private String positionAddress;

    private Date createTime;

    private Date updateTime;

    private String createUserName;

    private String updateUserName;

    private String createUserId;

    private String updateUserId;

    private String organId;

    private Integer del;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getFirstResourceType() {
        return firstResourceType;
    }

    public void setFirstResourceType(String firstResourceType) {
        this.firstResourceType = firstResourceType;
    }

    public String getSecondResourceType() {
        return secondResourceType;
    }

    public void setSecondResourceType(String secondResourceType) {
        this.secondResourceType = secondResourceType;
    }

    public String getThirdResourceType() {
        return thirdResourceType;
    }

    public void setThirdResourceType(String thirdResourceType) {
        this.thirdResourceType = thirdResourceType;
    }

    public Integer getHealthDegree() {
        return healthDegree;
    }

    public void setHealthDegree(Integer healthDegree) {
        this.healthDegree = healthDegree;
    }

    public BigDecimal getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(BigDecimal referencePrice) {
        this.referencePrice = referencePrice;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId == null ? null : partId.trim();
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getElevatorNum() {
        return elevatorNum;
    }

    public void setElevatorNum(String elevatorNum) {
        this.elevatorNum = elevatorNum == null ? null : elevatorNum.trim();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getPositionAddress() {
        return positionAddress;
    }

    public void setPositionAddress(String positionAddress) {
        this.positionAddress = positionAddress == null ? null : positionAddress.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId == null ? null : organId.trim();
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
