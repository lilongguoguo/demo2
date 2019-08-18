package com.common.district.res.service.impl;

import com.common.district.common.SysUtil.RespData;
import com.common.district.res.dao.*;
import com.common.district.res.model.*;
import com.common.district.res.service.SelectToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectToolServiceImpl implements SelectToolService {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;
    @Autowired
    private DictsMapper dictsMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public RespData getResourceType(String organId, String parentId) {
        List<ResourceType> list = null;
        if ("0".equals(parentId)) {
            list = resourceTypeMapper.selectResourceTypeByLevel(organId,1);
        } else {
            list = resourceTypeMapper.getResourceType(organId, parentId);
        }
        return RespData.success(list);
    }

    @Override
    public RespData getDictData(String organId, String type) {
        List<Dicts> list = dictsMapper.getDictsByType(organId, type);
        return RespData.success(list);
    }

    @Override
    public RespData getCompany(String organId) {
        List<Company> list = companyMapper.getCompany(organId);
        return RespData.success(list);
    }

    @Override
    public RespData getDistrict(String organId, String companyId) {
        List<District> list = districtMapper.getDistrict(organId, companyId);
        return RespData.success(list);
    }

    @Override
    public RespData getProject(String organId, String regionId) {
        List<Project> list = projectMapper.getProject(organId, regionId);
        return RespData.success(list);
    }

    @Override
    public RespData getProvince() throws Exception{
        List<Region> province = regionMapper.getProvince();
        return RespData.success(province);
    }

    @Override
    public RespData getChildRegionList(String parentId) throws Exception {
        List<Region> regions = regionMapper.selectByParentId(parentId);
        return RespData.success(regions);
    }

    /*@Override
    public RespData getBusinessType(String organId,String dictsType) throws Exception {
        List<Dicts> dictsByType = dictsMapper.getDictsByType(organId, dictsType);
        return RespData.success(dictsByType);
    }*/
}
