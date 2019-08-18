package com.common.district.res.service.impl;

import com.common.district.common.constants.ConstantsEnum;
import com.common.district.res.dao.DictsMapper;
import com.common.district.res.model.Dicts;
import com.common.district.res.service.DictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictsServiceImpl implements DictsService {
    @Autowired
    private DictsMapper dictsMapper;
    @Override
    public int insertSelective(Dicts record) {
        return dictsMapper.insertSelective(record);
    }

    @Override
    public Dicts selectByPrimaryKey(Long id) {
        return dictsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Dicts record) {
        return dictsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Dicts> getDictsByType(String organId, String type) {
        return dictsMapper.getDictsByType(organId,type);
    }

    @Override
    public List<Dicts> getDictsByType(String organId, ConstantsEnum.DictsTypeEnum dictsTypeEnum) {
        return  dictsMapper.getDictsByType(organId,dictsTypeEnum.getValue());
    }
}
