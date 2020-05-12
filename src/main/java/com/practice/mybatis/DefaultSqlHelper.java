package com.practice.mybatis;

import java.util.List;

public interface DefaultSqlHelper {

    public <E> List<E> executeQueryE(String sql, String[] parameters,Class<?> clazz);
}
