package com.codebase.framework.spring.reconstruct.bean.step2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
@RequiredArgsConstructor
@Getter
public class PropertyValue {

    private final String name;
    private final Object value;

}
