package com.spring.cloud.test;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient(value = "eureka-client")：指定调用eureka-client服务
 * fallback：熔断器的信息类
 * configuration：FeignConfig 配置类
 * @author SongJiuHua.
 * @Date Created on 2017/11/22.
 * @description
 */
@FeignClient(value = "eureka-client", fallback = TestInterfaceImpl.class, configuration = FeignConfig.class)
public interface TestInterface {

    /**
     * 调用eureka-client服务的testHi接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/testHi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
