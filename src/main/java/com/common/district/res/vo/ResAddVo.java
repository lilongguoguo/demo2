package com.common.district.res.vo;

import java.io.Serializable;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 11:41 2019/7/31
 */
public class ResAddVo implements Serializable {
    private static final long serialVersionUID = 1587385900089513685L;

    private String name;
    private Integer level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
