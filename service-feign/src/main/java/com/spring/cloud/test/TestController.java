package com.spring.cloud.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SongJiuHua.
 * @Date Created on 2017/11/22.
 * @description
 */
@RestController
public class TestController {

    @Autowired
    private TestInterface testInterface;

    @RequestMapping(value = "/feignHi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return testInterface.sayHiFromClientOne(name);
    }

}
