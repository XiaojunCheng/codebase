package com.codebase.framework.asm.basic.clazz;

import com.codebase.framework.asm.basic.BasicUsage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ClazzAnnotation
public final class Clazz {

    private Integer key;
    private BasicUsage basicUsage;

    public void show() {
        System.out.println(key + "," + basicUsage.getClass().getName());
        Test t = new Test();
        t.tShow();
    }

    @Data
    class Test {

        private Integer tKey;
        private BasicUsage tBasicUsage;

        public void tShow() {
            System.out.println(tKey + "," + tBasicUsage.getClass().getName());
        }
    }
}

