package com.common.district.res.vo;

import java.io.Serializable;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 14:51 2019/7/31
 */
public class AssociationResVo implements Serializable {
    private static final long serialVersionUID = -68575892781886544L;

    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
