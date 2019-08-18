package com.common.district.res.service.impl;

import com.common.district.common.utils.StringUtils;
import com.common.district.res.dao.ResourceTypeMapper;
import com.common.district.res.dto.ResourceTypeDTO;
import com.common.district.res.model.ResourceType;
import com.common.district.res.service.ResourceConfigService;
import com.common.district.res.util.GenerateIdUtils;
import com.common.district.res.vo.ResourceTypeVO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 14:20 2019/7/29
 */
@Service
public class ResourceConfigServiceImpl implements ResourceConfigService {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    @Override
    public List<ResourceTypeDTO> getResourceTypeList(String organId, Integer level) {
        List<ResourceType> resourceTypes = resourceTypeMapper.selectResourceTypeByLevel(organId, level);
        List<ResourceTypeDTO> resTypes = Lists.newArrayList();

        if (CollectionUtils.isNotEmpty(resourceTypes)) {
            resourceTypes.forEach(item -> {
                ResourceTypeDTO resourceTypeDTO = new ResourceTypeDTO();
                resourceTypeDTO.setId(item.getId());
                resourceTypeDTO.setResourceName(item.getResourceName());
                resourceTypeDTO.setParentId(item.getParentId());
                resourceTypeDTO.setResourceLevel(item.getResourceLevel());

                resTypes.add(resourceTypeDTO);
            });
        }
        return resTypes;
    }

    @Override
    public boolean removeResource(String organId, String id) {

        if (StringUtils.isBlank(id)) {
            return false;
        }

        try {
            ResourceType resourceType = resourceTypeMapper.selectByPrimaryKey(id);

            if (resourceType == null) {
                return false;
            }
            //如果是一级资源，还需要删除其下2级资源
            if (Objects.equals(1, resourceType.getResourceLevel())) {
                List<ResourceType> resourceTypes = resourceTypeMapper.selectResourceTypeByParentId(organId, id);

                if (CollectionUtils.isNotEmpty(resourceTypes)) {
                    resourceTypes.forEach(item -> {
                        item.setDel(1);
                        item.setUpdateTime(new Date());
                        resourceTypeMapper.updateByPrimaryKeySelective(item);
                    });
                }
            }
            //删除该资源
            resourceType.setDel(1);
            resourceType.setUpdateTime(new Date());
            resourceTypeMapper.updateByPrimaryKeySelective(resourceType);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ResourceTypeDTO> getSubResourceTypeList(String organId, String parentId) {

        List<ResourceTypeDTO> resultLists = Lists.newArrayList();
        //查询出该租户下所有的二级资源
        List<ResourceType> secondTypes = resourceTypeMapper.selectResourceTypeByLevel(organId, 2);
        //查询出该一级资源下的二级资源
        List<ResourceType> resourceTypes = resourceTypeMapper.selectResourceTypeByParentId(organId, parentId);

        if (CollectionUtils.isNotEmpty(secondTypes)) {
            if (CollectionUtils.isNotEmpty(resourceTypes)) {
                secondTypes.forEach(item -> {
                    ResourceTypeDTO resourceTypeDTO = new ResourceTypeDTO();
                    resourceTypeDTO.setId(item.getId());
                    resourceTypeDTO.setResourceName(item.getResourceName());
                    resourceTypeDTO.setParentId(item.getParentId());
                    resourceTypeDTO.setResourceLevel(item.getResourceLevel());
                    resourceTypeDTO.setChecked(false);
                    if (resourceTypes.contains(item)) {
                        resourceTypeDTO.setChecked(true);
                    }
                    resultLists.add(resourceTypeDTO);
                });
            } else {
                secondTypes.forEach(item -> {
                    ResourceTypeDTO resourceTypeDTO = new ResourceTypeDTO();
                    resourceTypeDTO.setId(item.getId());
                    resourceTypeDTO.setResourceName(item.getResourceName());
                    resourceTypeDTO.setParentId(item.getParentId());
                    resourceTypeDTO.setResourceLevel(item.getResourceLevel());
                    resourceTypeDTO.setChecked(false);
                    resultLists.add(resourceTypeDTO);
                });
            }
        }

        return resultLists;
    }

    @Override
    public boolean checkResourceByName(String organId, String name) {

        if(StringUtils.isBlank(name)){
            return false;
        }
        String[] split = name.split("\n");
        //name格式前端未定，暂不做NPE判断
        //判断用户输入的多行有没有重复
        List<String> strList =Lists.newArrayList();
        for (int i = 0; i < split.length; i++) {
            if(StringUtils.isBlank(split[i])){
                continue;
            }
            String str = split[i].replaceAll(" ", "");
            if(CollectionUtils.isNotEmpty(strList) && strList.contains(str)){
                return false;
            }
            ResourceType resourceType = resourceTypeMapper.selectByName(organId, str);

            if (resourceType != null) {
                return false;
            }
            strList.add(str);
        }

        return true;
    }

    @Override
    public boolean addResourceByName(String organId, String name, Integer level) {
        if (level == null || StringUtils.isBlank(name)) {
            return false;
        }
        try {
            String[] split = name.split("\n");
            for(int i=0;i<split.length;i++){
                ResourceType resourceType = new ResourceType();
                resourceType.setId(GenerateIdUtils.getId("ZYLX"));
                resourceType.setParentId("0");
                resourceType.setResourceName(split[i]);
                resourceType.setCreateTime(new Date());
                resourceType.setUpdateTime(new Date());
                resourceType.setOrganId(organId);
                resourceType.setDel(0);
                resourceType.setResourceLevel(level);
                resourceTypeMapper.insertSelective(resourceType);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addChildResource(String organId, ResourceTypeVO resourceTypeVO) {

        if (resourceTypeVO == null || resourceTypeVO.getResourceLevel() == null) {
            return false;
        }

        try {
            int lv = resourceTypeVO.getResourceLevel();

            List<ResourceTypeVO> childRes = resourceTypeVO.getChildRes();

            //首先删除该级下的所有子级资源
            resourceTypeMapper.deleteByParentId(organId, resourceTypeVO.getId(), lv + 1);

            if (CollectionUtils.isNotEmpty(childRes)) {
                childRes.forEach(item -> {
                    ResourceType resourceType = new ResourceType();
                    resourceType.setId(GenerateIdUtils.getId("ZYLX"));
                    resourceType.setParentId(resourceTypeVO.getId());
                    resourceType.setResourceName(item.getResourceName());
                    resourceType.setCreateTime(new Date());
                    resourceType.setUpdateTime(new Date());
                    resourceType.setOrganId(organId);
                    resourceType.setDel(0);
                    resourceType.setResourceLevel(lv + 1);
                    resourceTypeMapper.insertSelective(resourceType);
                });
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ResourceTypeDTO> getAssociationRes(String organId, String parentId) {
        List<ResourceTypeDTO> resultLists = Lists.newArrayList();
        //查询出该一级资源下的二级资源
        List<ResourceType> resourceTypes = resourceTypeMapper.selectResourceTypeByParentId(organId, parentId);

        if (CollectionUtils.isNotEmpty(resourceTypes)) {

            resourceTypes.forEach(item -> {
                ResourceTypeDTO resourceTypeDTO = new ResourceTypeDTO();
                resourceTypeDTO.setId(item.getId());
                resourceTypeDTO.setResourceName(item.getResourceName());
                resourceTypeDTO.setParentId(item.getParentId());
                resourceTypeDTO.setResourceLevel(item.getResourceLevel());
                resourceTypeDTO.setChecked(false);
                resultLists.add(resourceTypeDTO);
            });
        }

        return resultLists;
    }
}
