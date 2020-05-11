package com.practice.mybatis;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/8 9:36
 * @Version 1.0
 */
public class MapperStatement {
    private String sql;
    private String id;
    private String namespace;
    //sql的类型 insert update select delete
    private String StatementType;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getStatementType() {
        return StatementType;
    }

    public void setStatementType(String statementType) {
        StatementType = statementType;
    }
}
