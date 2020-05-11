package com.practice.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/11 9:42
 * @Version 1.0
 */
public class DefaultExecutor implements Executor{
    DefaultSqlHelper defaultSqlHelper;
    public DefaultExecutor(SqlHelper sqlHelper){
        this.defaultSqlHelper=sqlHelper;
    }

    @Override
    public <E> List<E> query(MapperStatement ms, Object parameter) {
        List<E> es = defaultSqlHelper.<E>executeQueryE(ms.getSql(), null);
        return es;
    }
}
