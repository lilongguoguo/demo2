package com.common.district.res.dao;

import com.common.district.res.model.Position;
import com.common.district.res.vo.ContractPositionVO;
import com.common.district.res.vo.PositionExportVO;
import com.common.district.res.vo.PositionUnitVO;
import com.common.district.res.vo.PositionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    int deletePosition(@Param("id")Long id,@Param("userId") String userId,@Param("userName") String userName);

    List<PositionVO> selectPageList(@Param("search") PositionVO positionVO);

    List<Position> getPosition(@Param("search") PositionVO positionVO);

    int getCountByPositionId(Long positionId);

    List<PositionExportVO> selectPositionList(@Param("search") PositionVO positionVO);

    Position selectByNameAndOrganId(@Param("positionCode") String positionCode,@Param("organId")String organId);

    int insertPositionList(@Param("list") List<Position> list);

    List<PositionVO> getFreePositionList(@Param("search") ContractPositionVO positionSearchVO);

    List<PositionUnitVO> selectPositionCodes(@Param("locationId")String stageId, @Param("locationType")Integer locationType, @Param("status")Integer status, @Param("resourcesId")String resourceId);

    Position selectByPositionCode(@Param("positionCode")String positionCode);

    //查询点位对应的合同id
    Long selectContIdByprojectId(@Param("projectId")String projectId);

    //根据projectId查询项目下的点位数量
    int getPositionCountByProjectId(@Param("projectId")String projectId, @Param("organId")String organId);
}