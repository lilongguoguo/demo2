package com.common.district.res.service;

import com.common.district.common.constants.ConstantsEnum;
import com.common.district.res.model.Dicts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictsService {
    int insertSelective(Dicts record);

    Dicts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dicts record);

    List<Dicts> getDictsByType(String organId,String type);

    List<Dicts> getDictsByType(String organId,ConstantsEnum.DictsTypeEnum dictsTypeEnum);
}