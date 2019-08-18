package com.common.district.res.service.impl;

import com.common.district.common.Constant;
import com.common.district.common.SysUtil.PageInfo;
import com.common.district.common.SysUtil.RespData;
import com.common.district.common.utils.StringUtils;
import com.common.district.res.dao.CustomerContactMapper;
import com.common.district.res.dao.CustomerMapper;
import com.common.district.res.dao.LogMapper;
import com.common.district.res.model.Customer;
import com.common.district.res.model.CustomerContact;
import com.common.district.res.model.Log;
import com.common.district.res.service.CustomerService;
import com.common.district.res.vo.CustomerContactVO;
import com.common.district.res.vo.CustomerVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerContactMapper customerContactMapper;
    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Customer> getCustomerList(CustomerVO customerVO, String organId, Integer pageNum, Integer pageSize) {
        return customerMapper.getCustomerList(customerVO, organId, pageNum, pageSize);
    }

    @Override
    public Integer getCustomerListCount(CustomerVO customerVO, String organId) {
        return customerMapper.getCustomerListCount(customerVO, organId);
    }

    @Override
    public RespData saveCustomerAndContact(CustomerVO customerVO, String organId) {
        if (null != customerVO){
            if (StringUtils.isBlank(customerVO.getCustomerName()) || StringUtils.isBlank(customerVO.getProvince()) ||
                    StringUtils.isBlank(customerVO.getCity()) || StringUtils.isBlank(customerVO.getIndustry()) ||
                    StringUtils.isBlank(customerVO.getCustomerSource()) || StringUtils.isBlank(customerVO.getCustomerCategory()) ||
                    StringUtils.isBlank(customerVO.getBusinessId()) || StringUtils.isBlank(customerVO.getCustomerScale()) ||
                    StringUtils.isBlank(customerVO.getCustomerType())){
                return RespData.fail("必填项为空！");
            }
            Log log = new Log();
            //新增
            if (StringUtils.isBlank(customerVO.getId())){
                //保存客户信息
                Customer saveCustomer = customerVO.initCustomer();
//                saveCustomer.setDepartId(customerVO.getDepartId());
//                saveCustomer.setDepartName(customerVO.getDepartName());
//                saveCustomer.setCreateCompanyId(customerVO.getCreateCompanyId());
//                saveCustomer.setCreateCompanyName(customerVO.getCreateCompanyName());
                saveCustomer.setCreateUserId(customerVO.getCreateUserId());
                saveCustomer.setCreateUserName(customerVO.getCreateUserName());
                saveCustomer.setOrganId(organId);
                int saveCount = customerMapper.insertSelective(saveCustomer);
                //保存客户联系人信息
                if (!CollectionUtils.isEmpty(customerVO.getCustomerContactList()) && saveCount == 1){
                    for (CustomerContact customerContact : customerVO.getCustomerContactList()) {
                        customerContact.setCustomerId(saveCustomer.getId());
                        customerContactMapper.insertSelective(customerContact);
                    }
                    log.setRelatedId(saveCustomer.getId().toString());
                    //类型暂时未定
                    log.setRelatedType(1L);
                    log.setContent("新增客户信息");
                    log.setCreateUserId("");
                    log.setCreateUserName("");
                    logMapper.insertSelective(log);
                }
                if (saveCount == 1){
                    return RespData.success("保存成功！");
                }
            }else {
                //修改只修改客户表信息，客户联系人单独修改
                Customer customer = customerMapper.selectByPrimaryKey(customerVO.getId());
                customerVO.setup(customer);
                customer.setUpdateUserId(customerVO.getUpdateUserId());
                customer.setUpdateUserName(customerVO.getUpdateUserName());
                customer.setUpdateTime(new Date());
                int updateCount = customerMapper.updateByPrimaryKeySelective(customer);
//                if (!CollectionUtils.isEmpty(customerVO.getCustomerContactList()) && updateCount == 1){
//                    for (CustomerContact customerContact : customerVO.getCustomerContactList()) {
//                        //修改联系人
//                        customerContactMapper.deleteByCustomerId(customer.getId());
//                        customerContactMapper.insertSelective(customerContact);
//                    }
//                }
                log.setRelatedId(customerVO.getId().toString());
                //类型暂时未定
                log.setRelatedType(1L);
                log.setContent("修改客户信息");
                log.setCreateUserId("");
                log.setCreateUserName("");
                logMapper.insertSelective(log);
                if (updateCount == 1){
                    return RespData.success("保存成功！");
                }
            }
        }
        return RespData.fail("保存失败！");
    }

    @Override
    public CustomerVO getCustomerDetail(Long customerId) {
        //获取基本客户信息
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        CustomerVO customerVO = new CustomerVO();
        BeanUtils.copyProperties(customer,customerVO);
        //获取客户对应的联系人信息
        List<CustomerContact> customerContactList  = customerContactMapper.selectListByCustomerId(customerId);
        if (!CollectionUtils.isEmpty(customerContactList)) {
            customerVO.setCustomerContactList(customerContactList);
        }
        List<Log> logList = logMapper.selectByCustomerId(customerId);
        if (!CollectionUtils.isEmpty(logList)) {
            customerVO.setLogList(logList);
        }
        return customerVO;
    }

    @Override
    public List<Customer> getCustomerListByOrganIdAndConLst(List<String> companyList, String organId) {
        return customerMapper.getCustomerListByOrganIdAndConLst(companyList,organId);
    }

    @Override
    public RespData delCustomer(Long customerId) {
        int i = customerMapper.deleteByPrimaryKey(customerId);
        if (0 < i){
            customerContactMapper.deleteByCustomerId(customerId);
            return  RespData.success("删除成功！");
        }
        return RespData.fail("删除失败！");
    }

    @Override
    public PageInfo<HashMap<String, Object>> getCusIdAndName(String organId, CustomerVO customerVO) {
        Integer pageSize = (null == customerVO.getPageSize() || customerVO.getPageNum() <= 0) ? Constant.DEFAULT_PAGE_SIZE : customerVO.getPageSize();
        Integer pageNum = (null == customerVO.getPageNum() || customerVO.getPageNum() <= 0) ? 1 : customerVO.getPageNum();
        // 限制查询最大条数
        if (Constant.MAX_PAGE_SIZE <= pageSize) {
            customerVO.setPageSize(Constant.MAX_PAGE_SIZE);
        }
        Page<?> page = PageHelper.startPage(pageNum, pageSize);
        List<HashMap<String,Object>> customerList = customerMapper.getCusIdAndName(organId, customerVO);
        return new PageInfo<HashMap<String,Object>>(page.getTotal(), customerList);
    }

    @Override
    public RespData<List<CustomerContact>> getCustomerContactList(Long customerId) {
        List<CustomerContact> customerContactList = customerContactMapper.getCustomerContactList(customerId);
        return RespData.success(customerContactList);
    }

    @Override
    public RespData saveOrUpdateCustomerContact(CustomerContactVO customerContactVO) {
        if (null != customerContactVO){
            if (StringUtils.isNotBlank(customerContactVO.getId())){
                CustomerContact customerContact = customerContactMapper.selectByPrimaryKey(customerContactVO.getId());
                customerContactVO.setup(customerContact);
                customerContactMapper.updateByPrimaryKeySelective(customerContact);
            }else {
                customerContactMapper.insertSelective(customerContactVO.initCustomerContact());
            }
            return RespData.success("保存联系人成功");
        }
        return RespData.fail("操作失败");
    }
}
