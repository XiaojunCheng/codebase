package com.codebase.framework.spring.schema;

import com.codebase.framework.spring.schema.bean.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by chengxiaojun on 17/2/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:schema.xml" })
public class SchemaTest {

    @Autowired
    @Qualifier("Ferrari458")
    private Car car;

    @Test
    public void propertyTest() {
        assertNotNull(car);

        String brand = car.getBrand();
        float engine = car.getEngine();
        int horsePower = car.getHorsePower();

        System.out.println(brand);
        assertEquals("Brand incorrect.Should be Ferrari.", "Ferrari", brand);
        assertEquals("Engine incorrect.Should be 4.5L.", 4.5, engine, 0.000001);
        assertEquals("HorsePower incorrect.Should be 605hp.", 605, horsePower);

    }
}
