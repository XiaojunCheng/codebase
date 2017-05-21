package com.codebase.framework.mybatis.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author Xiaojun.Cheng
 * @date 2017/4/1
 */

@Data
public class UserDO {

    private Long id;

    private String name;

    private Integer age;

    private String address;

}
