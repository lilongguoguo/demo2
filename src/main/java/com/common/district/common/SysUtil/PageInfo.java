package com.common.district.common.SysUtil;

import com.common.district.common.Constant;
import com.common.district.common.utils.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2019/7/29.
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = -1970281867989833568L;
    private Integer pageNum;
    private Integer pageSize = Constant.DEFAULT_PAGE_SIZE;
    private Long totalCount;
    // 总页数
    private Integer pages;
    private List<T> resultList;

    public PageInfo(){

    }

    public PageInfo(Long totalCount, List<T> resultList) {
        this.totalCount = totalCount;
        this.resultList = resultList;
    }

    public static final class PageInfoBuilder<T>{
        private Integer pageNum;
        private Integer pageSize;
        private Long totalCount;
        private Integer pages;
        private List<T> resultList;

        public static PageInfoBuilder anPageInfoDto(){return new PageInfoBuilder();}

        public PageInfoBuilder withPageNum(Integer pageNum){
            this.pageNum = pageNum;
            return this;
        }

        public PageInfoBuilder withPageSize(Integer pageSize){
            this.pageSize = pageSize;
            return this;
        }

        public PageInfoBuilder withTotalCount(Long totalCount){
            this.totalCount = totalCount;
            return this;
        }
        public PageInfoBuilder withTotalCount(Integer totalCount){
            this.totalCount=StringUtils.isBlank(totalCount)?this.totalCount:totalCount.longValue();
            return this;
        }
        public PageInfoBuilder withPages(Integer pages){
            this.pages = pages;
            return this;
        }

        public PageInfoBuilder withResultList(List<T> resultList){
            this.resultList = resultList;
            return this;
        }

        public PageInfo build() {
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPageNum(pageNum);
            pageInfo.setPageSize(pageSize);
            pageInfo.setTotalCount(totalCount);
            pageInfo.setPages(pages);
            pageInfo.setResultList(resultList);
            return pageInfo;
        }
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


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}
