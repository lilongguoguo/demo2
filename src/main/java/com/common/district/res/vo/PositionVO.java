package com.common.district.res.vo;

import java.math.BigDecimal;
import java.util.Date;

public class PositionVO {
    private Long id;
    // 点位名称
    private String positionName;
    // 点位编号
    private String positionCode;
    // 一级资源类型id
    private String firstResourceType;
    //一级资源类型
    private String firstResourceTypeName;
    // 二级资源类型id
    private String secondResourceType;
    //二级资源类型
    private String secondResourceTypeName;
    // 三级资源类型id
    private String thirdResourceType;
    // 三级资源类型
    private String thirdResourceTypeName;
    // 健康度
    private Integer healthDegree;
    private String healthDegreeName;
    // 参考价格
    private BigDecimal referencePrice;
    // 容量
    private BigDecimal capacity;
    // 容量单位
    private Integer capacityUnitId;
    // 规格
    private BigDecimal spec;
    // 规格单位
    private Integer specUnitId;
    // 公司id
    private String companyId;
    // 公司
    private String companyName;
    // 片区id
    private String areaId;
    // 片区
    private String areaName;
    // 项目表id
    private String projectId;
    //项目
    private String projectName;
    // 分期id
    private String partId;
    // 分期名称
    private String partName;
    // 楼栋id
    private String buildingId;
    // 楼栋名称
    private String buildingName;
    // 单元id
    private String unitId;
    // 单元名称
    private String unitName;
    // 电梯号
    private String elevatorNum;
    // 房间id
    private String roomId;
    // 房间号
    private String roomNum;
    // 点位位置
    private String positionAddress;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
    // 创建人
    private String createUserName;
    // 修改人
    private String updateUserName;
    // 创建人id
    private String createUserId;
    // 修改人id
    private String updateUserId;
    // 租户id
    private String organId;
    // 删除标志
    private Integer del;
    //合同id
    private Long contId;
    // 分页页数
    private Integer pageNum;
    // 分页页码
    private Integer pageSize;
    // 是否分页
    private boolean paging;

    private String startTime;
    private String endTime;

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
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

    public String getThirdResourceTypeName() {
        return thirdResourceTypeName;
    }

    public void setThirdResourceTypeName(String thirdResourceTypeName) {
        this.thirdResourceTypeName = thirdResourceTypeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }


}