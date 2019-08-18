package com.common.district.res.dao;

import com.common.district.res.model.Company;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Repository
public interface CompanyMapper {
    int deleteByPrimaryKey(String companyId);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(String companyId);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> selectAllByOrganId(String organId);

	List<Company> getCompany(String organId);
    // 根据公司名称和租户id查询公司
    Company selectByNameAndOrganId(@Param("companyName") String companyName,@Param("organId")String organId);
}