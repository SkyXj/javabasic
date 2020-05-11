package com.practice.mybatis;

import java.util.List;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/5/11 20:14
 * @Version 1.0
 */
public interface UserMapper {
    List<User> list();

    User getOne();
}
