package com.common.district.res.service;

import com.common.district.res.dto.ResourceTypeDTO;
import com.common.district.res.vo.ResourceTypeVO;

import java.util.List;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 14:17 2019/7/29
 */
public interface ResourceConfigService {

    /**
     * 根据级别查询资源
     * @param organId
     * @param level
     * @return
     */
    List<ResourceTypeDTO> getResourceTypeList(String organId, Integer level);

    /**
     * 删除资源
     */
    boolean removeResource(String organId,String id);

    /**
     * 查询子级资源
     * @param organId
     * @param parentId
     * @return
     */
    List<ResourceTypeDTO> getSubResourceTypeList(String organId, String parentId);

    /**
     * 资源名称判重
     * @param organId
     * @param name
     * @return
     */
    boolean checkResourceByName(String organId, String name);

    /**
     * 新增资源
     */
    boolean addResourceByName(String organId, String name,Integer level);

    /**
     * 新增子级资源
     */
    boolean addChildResource(String organId, ResourceTypeVO resourceTypeVO);

    /**
     * 查询关联资源
     * @param organId
     * @param parentId
     * @return
     */
    List<ResourceTypeDTO> getAssociationRes(String organId, String parentId);
}
