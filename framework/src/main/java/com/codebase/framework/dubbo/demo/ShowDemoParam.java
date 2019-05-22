package com.codebase.framework.dubbo.demo;

import lombok.Data;

import java.util.List;

/**
 * @author Xiaojun.Cheng
 * @date 2018/5/14
 */
@Data
public class ShowDemoParam {

    private Long demoId;

    private List<ShowDemoDetailParam> params;

}
