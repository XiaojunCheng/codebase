package com.codebase.framework.delayqueue;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Xiaojun.Cheng
 * @date 2018/12/26
 */
@Data
@NoArgsConstructor
public class Slot {

    private final ConcurrentMap<Task, TaskContext> taskMap = new ConcurrentHashMap<>();

    public void add(Task task, TaskContext taskContext) {
        taskMap.put(task, taskContext);
    }

    public Set<Task> filter() {
        Set<Task> filterTasks = new HashSet<>();
        for (Map.Entry<Task, TaskContext> entry : taskMap.entrySet()) {
            TaskContext taskContext = entry.getValue();
            if (taskContext.getCycleNum() == 0) {
                filterTasks.add(entry.getKey());
            }
        }
        return filterTasks;
    }

    public void reset() {
        for (Map.Entry<Task, TaskContext> entry : taskMap.entrySet()) {
            TaskContext taskContext = entry.getValue();
            if (taskContext.getCycleNum() == 0) {
                taskMap.remove(entry.getKey());
            } else {
                taskContext.setCycleNum(taskContext.getCycleNum()-1);
            }
        }
    }
}
