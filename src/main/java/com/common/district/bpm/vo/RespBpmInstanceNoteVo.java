package com.common.district.bpm.vo;

import java.io.Serializable;
import java.util.List;

public class RespBpmInstanceNoteVo implements Serializable {

    //模板id
    private Long templateId;
    //业务id
    private Integer relationId;
    //业务类型
    private Integer relationType;
    //节点信息
    List<BpmInstanceNoteVo> notes;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public List<BpmInstanceNoteVo> getNotes() {
        return notes;
    }

    public void setNotes(List<BpmInstanceNoteVo> notes) {
        this.notes = notes;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }
}
