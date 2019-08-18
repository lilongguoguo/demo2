package com.common.district.ad.vo;

import com.common.district.common.utils.Pagination;
import com.common.district.common.utils.StringUtils;

import java.util.Date;

public class ReceivablesBillDerateVo {
    private static final long serialVersionUID = -6849794470754667710L;
    /**应收帐单编号**/
    private String rbNo;
    /**合同编号**/
    private String contNo;
    /**合同所属公司id**/
    private String companyId;
    /**合同所属公司名字**/
    private String companyName;
    /**客户id**/
    private String cusId;
    /**客户名称**/
    private String cusName;
    /**项目id**/
    private String proId;
    /**项目名字**/
    private String proName;
    /**费用类型**/
    private Integer costType;
    /**费用类型名称**/
    private String costTypeName;
    /**合同经办人**/
    private String operatorName;
    /**应收开始日期**/
    private String rbStartDate;
    /**应收结束日期**/
    private String rbEndDate;
    /**违约金减免登记0否;1是**/
    private Integer  rbDerateRegister;
    /**应收账单状态0:未收款 1:已收款未到账 2:已到账 4冻结**/
    private Integer  rbStatus;
    /**减免开始日期**/
    private Date derateStartDate;
    /**减免结束日期**/
    private Date derateEndDate;
    /**减免提交人**/
    private String derateSubPerson;
    /**审批状态**/
    private Integer approveStatus;
    /**减免类型**/
    private Integer derateType;
    /**租户id**/
    private String organId;
    /**limit cp,ps**/
    private Integer cp;
    /**limit cp,ps**/
    private Integer ps;

    public String getRbNo() {
        return rbNo;
    }

    public void setRbNo(String rbNo) {
        this.rbNo = rbNo;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getRbStartDate() {
        return rbStartDate;
    }

    public void setRbStartDate(String rbStartDate) {
        this.rbStartDate = rbStartDate;
    }

    public String getRbEndDate() {
        return rbEndDate;
    }

    public void setRbEndDate(String rbEndDate) {
        this.rbEndDate = rbEndDate;
    }

    public Integer getRbDerateRegister() {
        return rbDerateRegister;
    }

    public void setRbDerateRegister(Integer rbDerateRegister) {
        this.rbDerateRegister = rbDerateRegister;
    }

    public Integer getRbStatus() {
        return rbStatus;
    }

    public void setRbStatus(Integer rbStatus) {
        this.rbStatus = rbStatus;
    }

    public Integer getCp() {
        return StringUtils.isBlank(cp)?0: Pagination.getCp(cp,getPs());
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Integer getPs() {
        return StringUtils.isBlank(ps)?10:ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDerateSubPerson() {
        return derateSubPerson;
    }

    public void setDerateSubPerson(String derateSubPerson) {
        this.derateSubPerson = derateSubPerson;
    }

    public Integer getDerateType() {
        return derateType;
    }

    public void setDerateType(Integer derateType) {
        this.derateType = derateType;
    }

    public Date getDerateStartDate() {
        return derateStartDate;
    }

    public void setDerateStartDate(Date derateStartDate) {
        this.derateStartDate = derateStartDate;
    }

    public Date getDerateEndDate() {
        return derateEndDate;
    }

    public void setDerateEndDate(Date derateEndDate) {
        this.derateEndDate = derateEndDate;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
}
