package com.common.district.bpm.dao;

import com.common.district.bpm.model.BpmInstanceNotePeople;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BpmInstanceNotePeopleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpmInstanceNotePeople record);

    int insertSelective(BpmInstanceNotePeople record);

    BpmInstanceNotePeople selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpmInstanceNotePeople record);

    int updateByPrimaryKey(BpmInstanceNotePeople record);

    /**
     * 批量保存节点人员信息
     * @param peopleList
     */
    void batchInsert(@Param("peopleList") List<BpmInstanceNotePeople> peopleList);

    /**
     * 查询当前执行节点的审批人审批状况
     * @param noteId
     * @return
     */
    List<BpmInstanceNotePeople> getCurrentPeopleByNoteId(@Param("noteId") Long noteId);
}