package com.common.district.res.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 15:21 2019/8/6
 */
public class LocationInfoVo implements Serializable {

    private static final long serialVersionUID = -796555706977301458L;

    private String locationId;

    private String name;

    private String locationName;

    private Integer locationType;

    private List<PositionUnitVO> positionIds;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public List<PositionUnitVO> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<PositionUnitVO> positionIds) {
        this.positionIds = positionIds;
    }
}
