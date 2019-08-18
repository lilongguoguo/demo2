package com.common.district.res.dao;

import com.common.district.res.model.Dicts;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DictsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dicts record);

    int insertSelective(Dicts record);

    Dicts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dicts record);

    int updateByPrimaryKey(Dicts record);

    List<Dicts> getDictsByType(@Param("organId")String organId, @Param("dictType")String type);

    /**
     *  查询全部部们类型
     * @param departmentOfType
     * @return
     */
    List<Dicts> selectByDepartmentOfType(@Param("departmentOfType")Long departmentOfType, @Param("organId")String organId);
}