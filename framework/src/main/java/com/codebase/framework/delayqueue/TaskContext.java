package com.codebase.framework.delayqueue;

import lombok.Data;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/26
 */
@Data
public class TaskContext {

    /**
     * 从当前index第几圈扫描到这个Slot时执行任务
     */
    private int cycleNum;
}
