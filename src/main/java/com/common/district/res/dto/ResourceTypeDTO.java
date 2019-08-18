package com.common.district.res.dto;

import java.io.Serializable;
import java.util.Date;

public class ResourceTypeDTO implements Serializable {
    private static final long serialVersionUID = 168348393265616275L;

    private String id;

    private String resourceName;

    private String parentId;

    private Integer resourceLevel;

    private boolean checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}