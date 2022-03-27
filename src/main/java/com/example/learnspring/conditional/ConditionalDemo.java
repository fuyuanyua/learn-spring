package com.example.learnspring.conditional;

import com.example.learnspring.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: 自定义条件装配注解示例
 * @Author: lhb
 * @Date: 2022/3/27 10:54
 *
 *      如何实现一个自定义条件装配注解？
 *          1.定义一个普通类A：需要实现Condition接口，实现matches方法，方法里自定义条件匹配逻辑
 *          2.定义一个注解类B：定义生效策略和作用范围，并加上注解@Conditional(类A.class)，表示该注解类B最终会执行类A里的matches方法
 *          3.使用注解类B：在类或方法上加上注解类B，容器会根据matches方法的逻辑判断是否要注入bean
 */

@Slf4j
public class ConditionalDemo {

    @Configuration
    static class Config {

        @Bean
        // 表示只有存在"com.example.learnspring.entity.Phone"才会注入car
//        @MyConditionalOnClass(className = "com.example.learnspring.entity.Phone")
        @MyConditionalOnClass(className = "com.example.learnspring.entity12345.Phone")
        public Car car() {
            return new Car("bwm", "blue");
        }
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();
        ctx.registerBean(ConfigurationClassPostProcessor.class);
        ctx.registerBean(Config.class);
        ctx.refresh();

        Object car = ctx.getBean("car");
        log.info("{}", car);
    }
}
