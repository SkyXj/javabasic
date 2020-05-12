package com.practice.mybatis;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/8 9:34
 * @Version 1.0
 */
public class DefaultSqlSession implements SqlSession{
    private final Configuration configuration;
    private final Executor executor;

    //执行器

    public DefaultSqlSession(Configuration configuration,Executor executor){
        this.configuration=configuration;
        this.executor=executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statement);
        executor.query(mapperStatement,null);
        return null;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement) {
        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statement);
        System.out.println(mapperStatement.getSql());
        SqlHelper sqlHelper=new SqlHelper(configuration);
        sqlHelper.executeQuery(mapperStatement.getSql(),null);
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return null;
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement) {
        return null;
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter) {
        return null;
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
        return null;
    }

    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {

    }

    @Override
    public void select(String statement, ResultHandler handler) {

    }

    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {

    }

    @Override
    public int insert(String statement) {
        return 0;
    }

    @Override
    public int insert(String statement, Object parameter) {
        return 0;
    }

    @Override
    public int update(String statement) {
        return 0;
    }

    @Override
    public int update(String statement, Object parameter) {
        return 0;
    }

    @Override
    public int delete(String statement) {
        return 0;
    }

    @Override
    public int delete(String statement, Object parameter) {
        return 0;
    }

    @Override
    public void commit() {

    }

    @Override
    public void commit(boolean force) {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void rollback(boolean force) {

    }

    @Override
    public List<BatchResult> flushStatements() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void clearCache() {

    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        T t=(T)Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(type.getName() + "." + method.getName());
                MapperStatement mapperStatement = configuration.getMapperStatementMap().get(type.getName() + "." + method.getName());
                Class<?> returnType = method.getReturnType();
                List list=executor.query(mapperStatement,null);
                //判断是否为数组
                if(Collection.class.isAssignableFrom(returnType)){
                    return list;
                }else{
                    if(list.size()==1){
                        return list.get(0);
                    }else if(list.size()>1){
                        throw new RuntimeException("Too Many Result!");
                    }
                }
                return null;
            }
        });
        return t;
    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
