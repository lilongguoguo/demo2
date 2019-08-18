package com.common.district.res.dao;


import com.common.district.res.model.Customer;
import com.common.district.res.vo.CustomerVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> getCustomerList(@Param("customerVO") CustomerVO customerVO, @Param("organId")String organId,
                                   @Param("pageNo")Integer pageNum, @Param("pageSize")Integer pageSize);

    Integer getCustomerListCount(@Param("customerVO") CustomerVO customerVO, @Param("organId")String organId);

    /**
     * 根据租户id和公司id集合获取客户信息集合
     * @param companyList 可空
     * @param organId 租户id
     * @auther fc
     * @return
     */
    List<Customer> getCustomerListByOrganIdAndConLst(@Param("comLst") List<String> companyList, @Param("organId")String organId);

    /**
     * 客户查询
     * @param organId
     * @param customerVO
     * @return
     */
    List<HashMap<String,Object>> getCusIdAndName(@Param("organId")String organId, @Param("search")CustomerVO customerVO);
}