package com.codebase.framework.dubbo.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiaojun.Cheng
 * @date 2018/5/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDemoDetailParam {

    private String demoName;

    private Long demoValue;
}
