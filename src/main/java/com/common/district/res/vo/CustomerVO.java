package com.common.district.res.vo;

import com.common.district.res.model.Customer;
import com.common.district.res.model.CustomerContact;
import com.common.district.res.model.Log;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerVO {
    private Long id;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiModelProperty("客户类别")
    private Integer customerCategory;
    @ApiModelProperty("客户行业")
    private Integer industry;
    @ApiModelProperty("客户等级")
    private Integer customerScale;
    @ApiModelProperty("客户类型")
    private Integer customerType;
    @ApiModelProperty("创建开始时间")
    private Date createTimeStart;
    @ApiModelProperty("创建结束时间")
    private Date createTimeEnd;


    @ApiModelProperty("客户来源")
    private Byte customerSource;
    @ApiModelProperty("主营业务")
    private Long businessId;
    @ApiModelProperty("营业额")
    private BigDecimal turnover;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("备注")
    private String customerRemarks;
    @ApiModelProperty("是否三证合一")
    private Integer isThreeNumber;
    @ApiModelProperty("三证编号")
    private String certificateNo;
    @ApiModelProperty("身份证号")
    private String idNumber;
    /**
     * 发票信息
     */
    @ApiModelProperty("纳税人识别号")
    private String ratepayerNo;
    @ApiModelProperty("开户银行")
    private String bankName;
    @ApiModelProperty("开户银行账号")
    private String bankAccount;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("客户地址(详细地址)")
    private String address;

    private String createUserName;

    private String createUserId;

    private String departId;

    private String departName;

    private String createCompanyId;

    private String createCompanyName;

    private String updateUserId;

    private String updateUserName;

    // 分页页数
    private Integer pageNum;
    // 分页页码
    private Integer pageSize;

    /**
     * 客户联系人表
     */
    @ApiModelProperty("客户联系人表")
    private List<CustomerContact> customerContactList;
    private List<Log> logList;

    public Customer initCustomer() {
        Customer customer = new Customer();
        customer.setCustomerStatus(1);//默认客户状态
        setup(customer);
        return customer;
    }

    public void setup(Customer customer) {
        customer.setCustomerName(customerName);//客户名称
        customer.setCustomerCategory(customerCategory);//客户类别
        customer.setIndustry(industry);//客户行业
        customer.setCustomerScale(customerScale);//客户等级
        customer.setCustomerType(customerType);
        customer.setCustomerSource(customerSource);//客户来源
        customer.setBusinessId(businessId);//主营业务
        customer.setTurnover(turnover);//营业额
        customer.setProvince(province);//省
        customer.setCity(city);//城市
        customer.setRemarks(customerRemarks);//备注
        //发票信息
        customer.setRatepayerNo(ratepayerNo);//纳税人识别号
        customer.setBankName(bankName);//开户银行
        customer.setBankAccount(bankAccount);//银行名称
        customer.setPhone(phone);//电话
        customer.setAddress(address);//详细地址
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(Integer customerCategory) {
        this.customerCategory = customerCategory;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getCustomerScale() {
        return customerScale;
    }

    public void setCustomerScale(Integer customerScale) {
        this.customerScale = customerScale;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Byte getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(Byte customerSource) {
        this.customerSource = customerSource;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomerRemarks() {
        return customerRemarks;
    }

    public void setCustomerRemarks(String customerRemarks) {
        this.customerRemarks = customerRemarks;
    }

    public Integer getIsThreeNumber() {
        return isThreeNumber;
    }

    public void setIsThreeNumber(Integer isThreeNumber) {
        this.isThreeNumber = isThreeNumber;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRatepayerNo() {
        return ratepayerNo;
    }

    public void setRatepayerNo(String ratepayerNo) {
        this.ratepayerNo = ratepayerNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<CustomerContact> getCustomerContactList() {
        return customerContactList;
    }

    public void setCustomerContactList(List<CustomerContact> customerContactList) {
        this.customerContactList = customerContactList;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getCreateCompanyId() {
        return createCompanyId;
    }

    public void setCreateCompanyId(String createCompanyId) {
        this.createCompanyId = createCompanyId;
    }

    public String getCreateCompanyName() {
        return createCompanyName;
    }

    public void setCreateCompanyName(String createCompanyName) {
        this.createCompanyName = createCompanyName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
