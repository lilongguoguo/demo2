package com.common.district.ad.dao;

import com.common.district.ad.model.UploadLog;
import com.common.district.res.vo.PositionVO;
import com.common.district.res.vo.UploadLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UploadLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UploadLog record);

    int insertSelective(UploadLog record);

    UploadLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UploadLog record);

    int updateByPrimaryKeyWithBLOBs(UploadLog record);

    int updateByPrimaryKey(UploadLog record);

    int getLogCount(@Param("organId")String organId,@Param("type")Integer type);

    List<UploadLogVO> selectPageList(@Param("search") UploadLogVO uploadLogVO);
}