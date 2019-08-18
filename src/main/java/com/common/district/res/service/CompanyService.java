package com.common.district.res.service;

import com.common.district.res.model.Company;

import java.util.List;

public interface CompanyService {

    /**
     * 根据租户id拿到当前租户下的公司集合
     * @param organId
     * @return
     */
    List<Company> selectAllByOrganId(String organId);
}
