package com.codebase.framework.spring.schema.bean;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/2/9.
 */
public class Car {

    private String brand;
    private float engine;
    private int horsePower;

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getEngine() {
        return engine;
    }

    public void setEngine(float engine) {
        this.engine = engine;
    }

}
