package com.common.district.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;

import java.util.logging.Handler;

/**
 * Created by YanjieG on 2017/8/24.
 */
public class PageUtils extends PageHelper {
    public static final Integer DEFAULT_PAGE_START = 1;
    public static final Integer NO_PAGE_COUNT = -1;
    public static final Integer DEFAULT_PAGE_SIZE = 100;

    public static Integer getForEachCount(Integer pageSize, Long total) {
        if (total < pageSize) {
            return 1;
        }
        Integer count = Integer.valueOf(total.toString());
        int pages = count / pageSize;
        if (count % pageSize == 0) {
            return pages;
        }
        return pages + 1;
    }

    public static Integer getForEachCount(Long total) {
        return getForEachCount(DEFAULT_PAGE_SIZE, total);
    }
    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     */
    public static Page startPage(int pageNum, int pageSize) {

        if (pageNum < 0) {
            return startPage(1, pageSize, false);
        }
        return startPage(pageNum, pageSize, true);
    }

    /**
     * 开始分页
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param orderBy  排序字段，正序直接输入排序字段即可 eg:"id" ，倒序输入排序字段+desc eg: "id desc"
     */
    public static Page startPage(int pageNum, int pageSize, String orderBy) {
        if (StringUtils.isNotBlank(orderBy)) {
            orderBy(orderBy);
        }
        return startPage(pageNum,pageSize);
    }
}
