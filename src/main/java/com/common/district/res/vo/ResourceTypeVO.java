package com.common.district.res.vo;

import java.io.Serializable;
import java.util.List;

public class ResourceTypeVO implements Serializable {

    private static final long serialVersionUID = 2133751446286415461L;

    private String id;

    private String resourceName;

    private Integer resourceLevel;

    List<ResourceTypeVO> childRes;

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public List<ResourceTypeVO> getChildRes() {
        return childRes;
    }

    public void setChildRes(List<ResourceTypeVO> childRes) {
        this.childRes = childRes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}