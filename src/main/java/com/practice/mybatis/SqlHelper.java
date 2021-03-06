package com.practice.mybatis;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/8 13:27
 * @Version 1.0
 */

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.sql.*;

public class SqlHelper implements DefaultSqlHelper{
    //定义变量
    private Connection ct = null;
    //大多数情况下用preparedstatement替代statement
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //连接数据库的参数
    private String url = null;
    private String username = null;
    private String driver = null;
    private String passwd = null;

    private CallableStatement cs = null;

    public CallableStatement getCs() {
        return cs;
    }

    public SqlHelper(Configuration configuration) {
        url = configuration.getUrl();
        username = configuration.getUsername();
        driver = configuration.getDrivername();
        passwd = configuration.getPassword();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //加载驱动，只需要一次，用静态代码块
//    
//    {
//        try
//        {
//            //从dbinfo.properties
//            pp = new Properties();
//            fis=SqlHelper.class.getClassLoader().getResourceAsStream("config.properties");
//            pp.load(fis);
//            url = pp.getProperty("url");
//            driver = pp.getProperty("driver");
//            username = pp.getProperty("user");
//            passwd = pp.getProperty("password");
//
//            Class.forName(driver);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            try{
//                fis.close();
//            }
//            catch(IOException e) {e.printStackTrace();}
//            fis = null;//垃圾回收站上收拾
//        }
//
//    }
    //得到连接
    public Connection getConnection() {
        try
        {ct = DriverManager.getConnection(url,username,passwd);}
        catch(Exception e) {e.printStackTrace();}
        return ct;
    }


    //*************callPro1存储过程函数1*************
    public CallableStatement callPro1(String sql, String[] parameters) {
        try {
            ct = getConnection();
            cs = ct.prepareCall(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    cs.setObject(i + 1, parameters[i]);
                }
            }
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, cs, ct);
        }
        return cs;
    }

    //*******************callpro2存储过程2************************
    public CallableStatement callPro2(String sql, String[] inparameters,
                                      Integer[] outparameters) {
        try {
            ct = getConnection();
            cs = ct.prepareCall(sql);
            if (inparameters != null) {
                for (int i = 0; i < inparameters.length; i++) {
                    cs.setObject(i + 1, inparameters[i]);
                }
            }
            //cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
            if (outparameters != null) {
                for (int i = 0; i < outparameters.length; i++) {
                    cs.registerOutParameter(inparameters.length + 1 + i, outparameters[i]);
                }
            }
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return cs;
    }

    /**
     * @param sql
     * @param parameters
     * @return ResultSet
     * @author Cally
     * @date 2017-8-27 上午10:32:00
     * @Description
     */
    public ArrayList<Object[]> executeQuery(String sql, String[] parameters) {
        ArrayList<Object[]> list = null;
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();

            //得到结果集（rs）的结构
            ResultSetMetaData rsmd = rs.getMetaData();
//            if(rs.getRow()!=0)
            list = new ArrayList<Object[]>();

            //通过rsmd可以得到该结果集有多少列
            int columnNum = rsmd.getColumnCount();
            //从rs中取出数据，并且封装到ArrayList中
            while (rs.next()) {
                Object[] objects = new Object[columnNum];
                for (int i = 0; i < objects.length; i++) {
                    objects[i] = rs.getObject(i + 1);
                    System.out.print(rsmd.getColumnName( i + 1 )+":"+objects[i]);
                }
                System.out.println();
                list.add(objects);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }
    }

    public Connection getCt() {
        return ct;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public ResultSet getRs() {
        return rs;
    }


    public void executeUpdate2(String[] sql, String[][] parameters) {
        try {
            ct = getConnection();
            ct.setAutoCommit(false);

            for (int i = 0; i < sql.length; i++) {

                if (null != parameters[i]) {
                    ps = ct.prepareStatement(sql[i]);
                    for (int j = 0; j < parameters[i].length; j++) {
                        ps.setString(j + 1, parameters[i][j]);
                    }
                    ps.executeUpdate();
                }

            }


            ct.commit();


        } catch (Exception e) {
            e.printStackTrace();
            try {
                ct.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }

    }

    //先写一个update、delete、insert
    //sql格式：update 表名 set 字段名 =？where 字段=？
    //parameter神应该是（”abc“,23）
    public void executeUpdate(String sql, String[] parameters) {
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }

            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();//开发阶段
            //抛出异常
            //可以处理，也可以不处理
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }
    }

    public void close(ResultSet rs, Statement ps, Connection ct) {
        //关闭资源(先开后关)
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ps = null;
        }
        if (null != ct) {
            try {
                ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ct = null;
        }
    }

    public List<Object> resultSetToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null) {
            return Collections.emptyList();
        }
        ResultSet md = (ResultSet) rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = ((ResultSetMetaData) md).getColumnCount(); //返回此 ResultSet 对象中的列数
        List<Object> list = new ArrayList<Object>();
        Map<Object, Object> rowData = new HashMap<Object, Object>();
        while (rs.next()) {
            rowData = new HashMap<Object, Object>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(((ResultSetMetaData) md).getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    @Override
    public <E> List<E> executeQueryE(String sql, String[] parameters,Class<?> clazz) {
        List<Object> list=null;
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
            list=handler(rs,clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }
        return (List<E>)list;
    }


    public List<Object> handler(ResultSet rs, Class<?> clazz) {
        List<Object> list = new ArrayList<>();
        Object obj = null;
        try {
            while (rs.next()) {
                // 创建一个clazz对象实例并将其赋给要返回的那个返回值。
                obj = clazz.newInstance();
                // 获取结果集的数据源
                ResultSetMetaData rsmeta = rs.getMetaData();

                // 获取结果集中的字段数
                int count = rsmeta.getColumnCount();

                // 循环取出个字段的名字以及他们的值并将其作为值赋给对应的实体对象的属性
                for (int i = 0; i < count; i++) {
                    // 获取字段名
                    String name = rsmeta.getColumnName(i + 1);
                    // 利用反射将结果集中的字段名与实体对象中的属性名相对应，由于
                    // 对象的属性都是私有的所以要想访问必须加上getDeclaredField(name)和
                    Field f = obj.getClass().getDeclaredField(name);
                    f.setAccessible(true);
                    // 将结果集中的值赋给相应的对象实体的属性
                    f.set(obj, rs.getObject(name));
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
