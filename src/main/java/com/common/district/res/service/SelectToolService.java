package com.common.district.res.service;

import com.common.district.common.SysUtil.RespData;
import com.common.district.res.model.ResourceType;

import java.util.List;

public interface SelectToolService {

    /**
     * 获取资源类型列表
     * @param organId
     * @param parentId
     * @return
     */
    RespData getResourceType(String organId, String parentId);

    /**
     * 根据字典类型获取字典数据
     * @param organId
     * @param type
     * @return
     */
    RespData getDictData(String organId, String type);

    /**
     * 获取公司列表
     * @param organId
     * @return
     */
    RespData getCompany(String organId);

    /**
     * 根据公司获取片区列表
     * @param organId
     * @return
     */
    RespData getDistrict(String organId, String companyId);


    /**
     * 根据片区获取项目列表
     * @param organId
     * @return
     */
    RespData getProject(String organId, String regionId);
    /**
     * @author tanwei
     * @description 获取省级列表
     * @date 2019/8/13 16:47
     * @param
     * @return
     */
    RespData getProvince() throws Exception;
    /**
     * @author tanwei
     * @description 获取子级城市
     * @date 2019/8/13 18:30
     * @param
     * @return
     */
    RespData getChildRegionList(String parentId) throws Exception;
    /**
     * @author tanwei
     * @description 获取业态
     * @date 2019/8/13 18:30
     * @param
     * @return
     */
    //RespData getBusinessType(String organId,String dictsType) throws Exception;
}
