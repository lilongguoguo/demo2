package com.common.district.res.service;

import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.res.model.Customer;
import com.common.district.res.model.CustomerContact;
import com.common.district.res.vo.CustomerContactVO;
import com.common.district.res.vo.CustomerVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface CustomerService {

    List<Customer> getCustomerList(CustomerVO customerVO, String organId, Integer pageNum, Integer pageSize);

    Integer getCustomerListCount(CustomerVO customerVO, String organId);

    RespData saveCustomerAndContact(CustomerVO customerVO, String organId);

    CustomerVO getCustomerDetail(Long customerId);
    /**
     * 根据租户id和公司id集合获取客户信息集合
     * @param companyList 可空
     * @param organId 租户id
     * @auther fc
     * @return
     */
    List<Customer> getCustomerListByOrganIdAndConLst(List<String> companyList, String organId);

    RespData delCustomer(Long customerId);

    PageInfo<HashMap<String,Object>> getCusIdAndName(String organId, CustomerVO customerVO);

    /**
     * 获取联系人数据
     * @param customerId
     * @return
     */
    RespData<List<CustomerContact>> getCustomerContactList(Long customerId);

    RespData saveOrUpdateCustomerContact(CustomerContactVO customerContactVO);
}