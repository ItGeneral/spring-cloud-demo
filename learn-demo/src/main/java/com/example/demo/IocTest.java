package com.example.demo;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author SongJiuHua.
 * @date Created on 2018/7/12.
 * @description 编程式使用IOC容器
 */
public class IocTest {

    /**
     * 创建IOC容器 读取Xml的bean配置
     * @param args
     */
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("bean.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
    }

}
