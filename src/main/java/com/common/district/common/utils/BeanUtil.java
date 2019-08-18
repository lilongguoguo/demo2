package com.common.district.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanUtil {

    /**
     * 将list里的bean复制到另一个list的bean里
     *
     * @param source 源list
     * @param target 目标list
     * @param type   目标bean类型
     * @return
     */
    public static <T> List<T> copyPropertiesForList(List source, List<T> target, Class<T> type) {

        if (source == null) {
            return null;
        }

        //循环复制bean
        target = target == null ? new ArrayList() : target;
        for (int i = 0; i < source.size(); i++) {
            T o = BeanUtils.instantiate(type);
            BeanUtils.copyProperties(source.get(i), o);
            target.add(o);
        }
        return target;
    }

}
