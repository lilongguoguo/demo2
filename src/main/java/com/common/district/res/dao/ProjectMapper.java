package com.common.district.res.dao;

import com.common.district.res.model.Customer;
import com.common.district.res.model.Project;
import com.common.district.res.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectMapper {
    int deleteByPrimaryKey(String projectId);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(String projectId);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    /**
     * 根据租户id和公司id集合获取项目信息集合
     * @param companyList 可空
     * @param organId 租户id
     * @auther fc
     * @return
     */
    List<Project> getProjectListByOrganIdAndConLst(@Param("comLst") List<String> companyList, @Param("organId")String organId);


	List<Project> getProject(@Param("organId")String organId, @Param("regionId")String regionId);
    // 根据项目名称和租户id查询项目
    Project selectByNameAndOrganId(@Param("projectName") String projectName,@Param("organId")String organId);

    List<PositionSalesControVO> selectPageList(@Param("search") PositionSalesControVO positionSalesControVO);

    List<PositionSalesControExportVO> selectPositionList(@Param("search") PositionSalesControVO positionSalesControVO);

    List<ProjectVo> selectProjectList(@Param("search") ProjectVo projectVo);

    int checkProject(@Param("organId")String organId, @Param("projectName")String projectName);
}