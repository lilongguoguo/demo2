package com.common.district.res.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: tanwei
 * @Description:
 * @Date: Create in 11:14 2019/8/2
 */
public class PositionImportVO implements Serializable {

    private static final long serialVersionUID = 7506114687587198043L;
    /**
     * 模板名
     */
    private String fileName;
    /**
     * 后缀
     */
    private String postfix;

    /**
     * url
     * @return
     */
    private String fileUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
