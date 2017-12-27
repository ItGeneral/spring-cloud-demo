package com.spring.cloud.test;


import org.springframework.stereotype.Component;

/**
 * @author SongJiuHua.
 * @Date Created on 2017/11/22.
 * @description
 */
@Component
public class TestInterfaceImpl implements TestInterface{

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }

}
