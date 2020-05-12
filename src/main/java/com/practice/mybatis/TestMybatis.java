package com.practice.mybatis;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/8 10:08
 * @Version 1.0
 */
public class TestMybatis {

    public static void main(String[] args) {
        Configuration configuration = getConfiguration();
        DefaultSqlSessionFactory sqlSessionFactory=new DefaultSqlSessionFactory(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.list();
        for (User user:list) {
            System.out.println(user.getUsername());
        }
    }

    public static Configuration getConfiguration(){
        Configuration configuration=new Configuration();

        Properties properties = new Properties();
        InputStream is = DefaultSqlSessionFactory.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            // 加载配置文件中的数据
            properties.load(is);
            // 创建连接池，使用配置文件中的参数
            String driver_class = properties.getProperty("DRIVER_CLASS");
            String db_url = properties.getProperty("DB_URL");
            String db_user = properties.getProperty("DB_USER");
            String db_password = properties.getProperty("DB_PASSWORD");

            configuration.setDrivername(driver_class);
            configuration.setUrl(db_url);
            configuration.setUsername(db_user);
            configuration.setPassword(db_password);
            configuration.setMapperStatementMap(getMapperStatementMap());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

    public static  Map<String,MapperStatement> getMapperStatementMap(){
        Map<String,MapperStatement> map=new HashMap<>();

        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = null;
        try {
            File directory = ResourceUtils.getFile("classpath:mapper");
            if(directory.isDirectory()){
                File[] files=directory.listFiles();
                for (File file:files){
                    reader.setValidation(false);
                    document = reader.read(file);
                    // 获取根节点元素对象
                    Element node = document.getRootElement();
//                    listNodes(node);
                    if(node.getName().equals("mapper")){
                        Attribute attribute = node.attribute(0);
                        String namespace = attribute.getValue();
                        Iterator<Element> it = node.elementIterator();
                        // 遍历
                        while (it.hasNext()) {
                            // 获取某个子节点对象
                            Element e = it.next();
                            Attribute attributetemp = e.attribute(0);
                            MapperStatement mapperStatement=new MapperStatement();
                            mapperStatement.setId(e.attributeValue("id"));
                            mapperStatement.setResultType(e.attributeValue("resultType"));
                            mapperStatement.setSql(e.getText().trim());
                            mapperStatement.setNamespace(namespace);
                            mapperStatement.setStatementType(e.getName());
                            map.put(namespace+"."+e.attributeValue("id"),mapperStatement);
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        for (String key:map.keySet()){
//            System.out.println(key);
//            MapperStatement mapperStatement = map.get(key);
//            System.out.println(mapperStatement.getId());
//            System.out.println(mapperStatement.getNamespace());
//            System.out.println(mapperStatement.getSql());
//            System.out.println(mapperStatement.getStatementType());
//        }
        return map;

    }

    public static void listNodes(Element node) {
        System.out.println("当前节点的名称：：" + node.getName());
        // 获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        // 遍历属性节点
        for (Attribute attr : list) {
            System.out.println(attr.getText() + "-----" + attr.getName() + "---" + attr.getValue());
        }

        if (!(node.getTextTrim().equals(""))) {
            System.out.println("文本内容：：：：" + node.getText());
        }

        // 当前节点下面子节点迭代器
        Iterator<Element> it = node.elementIterator();
        // 遍历
        while (it.hasNext()) {
            // 获取某个子节点对象
            Element e = it.next();
            // 对子节点进行遍历
            listNodes(e);
        }
    }

//    public static void main(String[] args){
//        List<User> user=new ArrayList<>();
//        System.out.println(user.getClass());
//    }
}
