package com.common.district.bpm.dao;

import com.common.district.bpm.model.BpmApproveRecords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BpmApproveRecordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BpmApproveRecords record);

    int insertSelective(BpmApproveRecords record);

    BpmApproveRecords selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BpmApproveRecords record);

    int updateByPrimaryKey(BpmApproveRecords record);

    /**
     * 根据实例id查询审批记录
     * @param instanceIds
     * @return
     */
    List<BpmApproveRecords> selectRecordList(@Param("instanceIds") List<Long> instanceIds);

}