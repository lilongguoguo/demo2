package com.common.district.bpm.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RespBpmButtonVo implements Serializable {

    private Boolean baseInfoShow = true;//是否可编辑基本页面

    private Boolean bpmShow = true;//是否可编辑bmp审批流配置页面

    private List<Map> buttonList; //权限按钮

    public Boolean getBaseInfoShow() {
        return baseInfoShow;
    }

    public void setBaseInfoShow(Boolean baseInfoShow) {
        this.baseInfoShow = baseInfoShow;
    }

    public Boolean getBpmShow() {
        return bpmShow;
    }

    public void setBpmShow(Boolean bpmShow) {
        this.bpmShow = bpmShow;
    }

    public List<Map> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Map> buttonList) {
        this.buttonList = buttonList;
    }
}
