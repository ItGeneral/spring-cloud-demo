package com.spring.cloud.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private TestService testService;

    @RequestMapping(value = "/serviceRibbon")
    public String invokeEurekaClient(@RequestParam String name){
        return testService.testService(name);
    }

}
