package com.practice.mybatis;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/8 9:49
 * @Version 1.0
 */
public interface SqlSessionFactory {
    SqlSession openSession();
//    org.apache.ibatis.session.SqlSession openSession(boolean autoCommit);
//    org.apache.ibatis.session.SqlSession openSession(Connection connection);
//    org.apache.ibatis.session.SqlSession openSession(TransactionIsolationLevel level);
//
//    org.apache.ibatis.session.SqlSession openSession(ExecutorType execType);
//    org.apache.ibatis.session.SqlSession openSession(ExecutorType execType, boolean autoCommit);
//    org.apache.ibatis.session.SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level);
//
//    SqlSession openSession(ExecutorType execType, Connection connection);

    Configuration getConfiguration();


}
