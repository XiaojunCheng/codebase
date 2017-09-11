package com.codebase.foundation.apidesign.v2;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/11
 */
public interface Function<From, To> {

    To map(From from);

}
