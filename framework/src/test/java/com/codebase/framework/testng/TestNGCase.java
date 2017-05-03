package com.codebase.framework.testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Xiaojun.Cheng
 * @date 2017/4/21
 */
//@Test
public class TestNGCase {

    /**
     * @DataProvider 用来定义数据源,测试用例可以引用此数据源
     *
     * 数据源:
     * 1. Excel (POI)
     * 2. DB
     * 3. Properties File
     *
     */
    @DataProvider(name = "mockData")
    public Object[][] data() {
        return new String[][]{
                new String[]{"data1"}, new String[]{"data2"}
        };
    }

    @Test(dataProvider = "mockData")
    public void test(String d) {
        System.out.println("data: " + d);
        Assert.assertEquals("a", "a");
    }

}
