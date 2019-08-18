package com.common.district.org.service;

import com.common.district.common.SysUtil.RespData;
import com.common.district.org.vo.BusRoleVo;
import com.common.district.org.vo.RoleVo;

import java.util.List;

/**
 * 角色设置信息
 */
public interface RoleService {
    /**
     * 查询角色信息
     */
    List<RoleVo> getAllRole(RoleVo vo);

    /**
     *角色列表分页统计数
     * @return
     */
    Integer countRole(RoleVo vo);

    /**
     * 查询角色设置配置（添加或修改查询）
     * @param vo
     * @return
     */
    RespData characterSet(BusRoleVo vo);

    /**
     * 添加或者修改角色菜单功能
     * @param vo
     * @return
     */
    RespData appendCharacterSet(BusRoleVo vo);
}
