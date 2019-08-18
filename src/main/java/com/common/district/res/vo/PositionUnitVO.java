package com.common.district.res.vo;

import java.io.Serializable;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 11:44 2019/8/7
 */
public class PositionUnitVO implements Serializable {

    private static final long serialVersionUID = 6547233122053493207L;

    private String positionCode;

    private Integer status;

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
