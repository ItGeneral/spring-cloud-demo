package com.spring.cloud.test;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SongJiuHua.
 * @Date Created on 2017/12/7.
 * @description
 */
@Configuration
public class FeignConfig {

    /**
     * 更改FeignClient的重试次数，重试间隔为100ms，最大重试时间为1s,重试次数为5次
     * @return
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1, 5);
    }

}
