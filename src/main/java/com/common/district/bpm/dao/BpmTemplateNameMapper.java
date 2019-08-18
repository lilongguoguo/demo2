package com.common.district.bpm.dao;

import com.common.district.bpm.model.BpmTemplateName;

public interface BpmTemplateNameMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpmTemplateName record);

    int insertSelective(BpmTemplateName record);

    BpmTemplateName selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpmTemplateName record);

    int updateByPrimaryKey(BpmTemplateName record);
}