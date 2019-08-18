package com.common.district.org.dao;

import com.common.district.org.model.User;
import com.common.district.org.vo.UserKey;
import com.common.district.org.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    User findUserByLoginId(@Param("loginId") String loginId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //查询角色列表人员信息
    List<UserKey> getRoleVoUser(@Param("set")Set<String> set);
	
	 /**
     * 获取单位下所有员工
     * @param companyId
     * @return
     */
	List<User> getAllDepUserByUserId(@Param("companyId") String companyId);

    List<User> getUserListByUserName(@Param("userName") String userName,
                                     @Param("organId")String organId,
                                     @Param("cp") Integer cp,
                                     @Param("ps") Integer ps);
    Integer getUserSizeByUserName(@Param("userName")String userName,
                                  @Param("organId")String organId);

    //获取人员，角色，部门
    List<User> getAllRoleDeptUser(User record);

    //人员账号列表分页统计
    List<User> getPersonnelListCount(User record);
}