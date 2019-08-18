package com.common.district.bpm.dao;

import com.common.district.bpm.model.BpmInstanceNote;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BpmInstanceNoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpmInstanceNote record);

    int insertSelective(BpmInstanceNote record);

    BpmInstanceNote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpmInstanceNote record);

    int updateByPrimaryKey(BpmInstanceNote record);

    /**
     * 根据实例id查询节点执行情况
     * @param instanceId
     * @return
     */
    List<Map> getNoteByInstanceId(@Param("instanceId") Long instanceId);

    /**
     * 查询当前正在执行节点
     * @param instanceId
     * @return
     */
    BpmInstanceNote getCurrentNoteByInstanceId(@Param("instanceId") Long instanceId);

    /**
     * 查询下一个节点
     * @param instanceId
     * @param noteSort
     * @return
     */
    BpmInstanceNote getNextNote(@Param("instanceId")Long instanceId, @Param("noteSort")Integer noteSort);
}