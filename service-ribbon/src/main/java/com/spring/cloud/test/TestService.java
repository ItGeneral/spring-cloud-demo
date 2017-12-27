package com.spring.cloud.test;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author SongJiuHua.
 * @Date Created on 2017/11/22.
 * @description
 */
@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用eureka-client服务的接口
     * HystrixCommand 注解对该方法创建了熔断器，发生错误后调用熔断方法hiError()
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String testService(String name){
        return restTemplate.getForObject("http://eureka-client/testHi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
