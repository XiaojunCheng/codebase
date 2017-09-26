package com.codebase.foundation.apidesign.functional;

/**
 * @author Xiaojun.Cheng
 * @date 2017/9/11
 */
public interface Specification<T> {

    boolean satisfiedBy(T item);

}
