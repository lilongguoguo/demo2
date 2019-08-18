package com.common.district.bpm.dao;

import com.common.district.bpm.model.BpmTemplate;
import org.apache.ibatis.annotations.Param;

public interface BpmTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpmTemplate record);

    int insertSelective(BpmTemplate record);

    BpmTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpmTemplate record);

    int updateByPrimaryKey(BpmTemplate record);

    /**
     * 根据模板code查询模板
     * @param templateCode
     * @return
     */
    BpmTemplate getByTemplateCode(@Param("code") String templateCode);
}