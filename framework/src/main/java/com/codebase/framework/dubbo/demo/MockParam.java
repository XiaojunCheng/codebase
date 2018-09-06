package com.codebase.framework.dubbo.demo;

import lombok.Data;

import java.util.List;

/**
 * @author Xiaojun.Cheng
 * @date 2018/5/14
 */
@Data
public class MockParam {

    private Integer id;

    private List<MockDetailParam> params;

}
