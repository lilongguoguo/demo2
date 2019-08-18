package com.common.district.bpm.vo;

import java.io.Serializable;
import java.util.List;

public class ApproveParam implements Serializable {


    //模板id
    private Long templateId;
    //业务id
    private Long relationId;
    //业务类型
    private Integer relationType;
    //节点信息
    private List<ApproveNoteParam> notes;
    //按钮操作值（bpm返回到前台的按钮value）
    private Integer operate;
    //审批内容
    String approveContent;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public List<ApproveNoteParam> getNotes() {
        return notes;
    }

    public void setNotes(List<ApproveNoteParam> notes) {
        this.notes = notes;
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }
}
