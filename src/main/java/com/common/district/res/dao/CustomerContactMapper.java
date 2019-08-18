package com.common.district.res.dao;

import com.common.district.res.model.CustomerContact;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CustomerContactMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerContact record);

    int insertSelective(CustomerContact record);

    CustomerContact selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerContact record);

    int updateByPrimaryKey(CustomerContact record);

    List<CustomerContact> selectListByCustomerId(Long customerId);

    int deleteByCustomerId(Long customerId);

    List<CustomerContact> getCustomerContactList(Long customerId);
}