package com.common.district.org.service;

import com.common.district.common.SysUtil.RespData;
import com.common.district.org.vo.UserVo;

import java.util.List;

/**
 * 人员账号信息
 */
public interface PersonnelService {

    //获取人员账号列表
    List getPersonnelList(UserVo vo);
    //获取人员账号列表统计数
    Integer getPersonnelListCount(UserVo vo);

    //部门角色名
    RespData getDeptRoleName(UserVo vo);
}
