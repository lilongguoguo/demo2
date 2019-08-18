package com.common.district.bpm.dao;

import com.common.district.bpm.model.BpmInstance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpmInstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpmInstance record);

    int insertSelective(BpmInstance record);

    BpmInstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpmInstance record);

    int updateByPrimaryKey(BpmInstance record);

    /**
     * 根据业务id 和类型 获取实例
     * @param relationId
     * @param relationType
     * @param del
     * @return
     */
    List<BpmInstance> getInstanceByRelationId(@Param("relationId") Long relationId, @Param("relationType") Integer relationType, @Param("del")Integer del);
}