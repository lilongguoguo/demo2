package com.common.district.common.log;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class})})
public class SqlLogger implements Interceptor {
    private final Logger _LOGGER = LoggerFactory.getLogger(this.getClass());
    /**
     * log the plugins if the excute  exceed this .default is 6s
     */
    private static Long exceedTime = 600l;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object returnValue = null;
        long start = 0l;
        MappedStatement mappedStatement = null;
        BoundSql boundSql = null;
        String sqlId = null;
        Configuration configuration = null;

        try {
            mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            sqlId = mappedStatement.getId();
            boundSql = mappedStatement.getBoundSql(parameter);
            configuration = mappedStatement.getConfiguration();
            start = System.currentTimeMillis();
        } catch (Throwable e) {
            _LOGGER.error("error", e);
        }

        returnValue = invocation.proceed();

        try {
            long end = System.currentTimeMillis();
            long time = (end - start);
            if (time >= exceedTime) {
                String sql = getSql(configuration, boundSql, sqlId, time);
                _LOGGER.info("*** sql is *** [{}] *** cost is *** {}ms *** has exceed the limit *** limit is *** {}ms ***", sql.split(":")[1], time, exceedTime);
                return returnValue;
            }
            if (_LOGGER.isDebugEnabled()) {
                String sql = getSql(configuration, boundSql, sqlId, time);
                _LOGGER.debug("*** sql is *** [{}] *** cost is *** {}ms ***", sql.split(":")[1], time);
            }
        } catch (Throwable e) {
            _LOGGER.error("error", e);
        }
        return returnValue;
    }

    private static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
        String sql = showSql(configuration, boundSql);
        StringBuilder str = new StringBuilder(100);
        str.append(sqlId);
        str.append(":");
        str.append(sql);
        str.append(":");
        str.append(time);
        str.append("ms");
        return str.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        if (properties == null) {
            return;
        }
        if (properties.isEmpty())
            return;
        String exccedtime = properties.getProperty("exccedtime");

        if (exccedtime == null || "".equals(exccedtime))
            return;
        try {
            long l = Long.parseLong(exccedtime);
            exceedTime = l;
        } catch (Exception e) {
            _LOGGER.error("error", e);
        }
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    private static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }
}
