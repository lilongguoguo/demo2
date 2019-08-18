package com.common.district.bpm.dao;

import com.common.district.bpm.model.BpmTemplateConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpmTemplateConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpmTemplateConfig record);

    int insertSelective(BpmTemplateConfig record);

    BpmTemplateConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpmTemplateConfig record);

    int updateByPrimaryKey(BpmTemplateConfig record);

    /**
     * 根据模板code获取标准节点信息
     * @param templateId
     * @return
     */
    List<BpmTemplateConfig> getconfigByTemplateId(@Param("templateId") Long templateId);
}