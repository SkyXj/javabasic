package com.practice.mybatis;





import java.sql.SQLException;
import java.util.List;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/11 9:41
 * @Version 1.0
 */
public interface Executor {

    <E> List<E> query(MapperStatement ms, Object parameter);

}
