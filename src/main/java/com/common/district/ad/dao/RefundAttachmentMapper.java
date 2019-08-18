package com.common.district.ad.dao;

import com.common.district.ad.model.RefundAttachment;

public interface RefundAttachmentMapper {
    int deleteByPrimaryKey(Long ratId);

    int insert(RefundAttachment record);

    int insertSelective(RefundAttachment record);

    RefundAttachment selectByPrimaryKey(Long ratId);

    int updateByPrimaryKeySelective(RefundAttachment record);

    int updateByPrimaryKey(RefundAttachment record);
}