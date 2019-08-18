package com.common.district.res.service.impl;

import com.common.district.res.dao.CompanyMapper;
import com.common.district.res.model.Company;
import com.common.district.res.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 公司业务类
 * @author fc
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public List<Company> selectAllByOrganId(String organId) {
        return companyMapper.selectAllByOrganId(organId);
    }
}
