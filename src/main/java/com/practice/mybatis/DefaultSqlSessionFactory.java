package com.practice.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/8 9:57
 * @Version 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private final Configuration config;

    public DefaultSqlSessionFactory(Configuration config){
        this.config=config;
    }

    @Override
    public SqlSession openSession() {
        Executor executor=new DefaultExecutor();
        return new DefaultSqlSession(config,executor);
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
