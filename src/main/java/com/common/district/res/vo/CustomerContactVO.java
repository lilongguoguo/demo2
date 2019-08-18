package com.common.district.res.vo;

import com.common.district.res.model.CustomerContact;
import io.swagger.annotations.ApiModelProperty;


public class CustomerContactVO {

    private Long id;

    @ApiModelProperty("客户id")
    private Long customerId;

    @ApiModelProperty("联系人名称")
    private String contactName;

    @ApiModelProperty("联系人电话")
    private String contactPhone;

    @ApiModelProperty("联系人职位")
    private String contactPosition;

    @ApiModelProperty("联系人邮箱")
    private String email;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("备注")
    private String remarks;


    public CustomerContact initCustomerContact(){
        CustomerContact customerContact = new CustomerContact();
        setup(customerContact);
        return customerContact;
    }

    public void  setup(CustomerContact customerContact){
        customerContact.setCustomerId(customerId);
        customerContact.setContactName(contactName);
        customerContact.setContactPhone(contactPhone);
        customerContact.setContactPosition(contactPosition);
        customerContact.setSex(sex);
        customerContact.setRemarks(remarks);
        customerContact.setEmail(email);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
