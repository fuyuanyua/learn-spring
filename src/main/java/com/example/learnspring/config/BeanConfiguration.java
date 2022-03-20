package com.example.learnspring.config;

import com.example.learnspring.beanlifecycle.InitAndDestroyDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: BeanConfiguration
 * @Author: lhb
 * @Date: 2022/3/20 21:01
 */

@Configuration
public class BeanConfiguration {

    @Bean(initMethod = "init3", destroyMethod = "destroy3")
    public InitAndDestroyDemo initAndDestroyDemo() {
        return new InitAndDestroyDemo();
    }
}
