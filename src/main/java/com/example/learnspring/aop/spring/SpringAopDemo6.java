package com.example.learnspring.aop.spring;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: Spring容器中AOP实现示例
 * @Author: lhb
 * @Date: 2022/3/25 11:40
 *
 *      bean生命周期：
 *          实例化 -> populateBean（填充属性） -> aware -> before -> init -> after
 *      说明：
 *          其中before和after都由BeanPostProcessor实现，AOP则在before或after这两个步骤的其中一个步骤完成
 */

public class SpringAopDemo6 {

    public static void main(String[] args) {
        // 纯净的Spring容器
        GenericApplicationContext ctx = new GenericApplicationContext();


    }

    @Configuration
    static class MyConfiguration {

        // BeanPostProcessor：解析@Aspect，生成代理对象
        @Bean
        public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator() {
            return new AnnotationAwareAspectJAutoProxyCreator();
        }

        // BeanPostProcessor：解析@Autowired
        @Bean
        public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
            return new AutowiredAnnotationBeanPostProcessor();
        }
        @AfterReturning
        @AfterThrowing
        // BeanPostProcessor：解析@PostConstruct等
        public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor() {
            return new CommonAnnotationBeanPostProcessor();
        }
    }
}
