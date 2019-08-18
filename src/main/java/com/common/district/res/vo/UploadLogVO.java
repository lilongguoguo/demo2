package com.common.district.res.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 11:14 2019/8/2
 */
public class UploadLogVO implements Serializable {
    private static final long serialVersionUID = -4764037805474644786L;
    /**
     * id
     */
    private Integer id;
    /**
     * 原始文件名
     */
    private String oldFileName;
    /**
     * 上传人id
     */
    private String createUserId;
    /**
     * 上传人姓名
     */
    private String createUserName;
    /**
     * 上传时间
     */
    private Date createTime;
    /**
     * 上传时间开始
     */
    private Date createTimeStart;
    /**
     * 上传时间结束
     */
    private Date createTimeEnd;
    /**
     * 编号
     */
    private String fileNo;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 租户id
     */
    private String organId;
    /**
     * 类型 1.点位导入
     */
    private Integer type;
    /**
     * 上传时间查询字段
     */
    private String[] uploadDate;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 是否分页
     */
    private boolean paging;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String[] getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String[] uploadDate) {
        this.uploadDate = uploadDate;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
