package com.codebase.framework.mybatis;

import lombok.Data;

import java.util.Date;

/**
 * @author Xiaojun.Cheng
 * @date 2017/4/1
 */

@Data
public class Blog {

    private Long userId;

    private Long articleId;

    private String context;

    private Date date;

}
