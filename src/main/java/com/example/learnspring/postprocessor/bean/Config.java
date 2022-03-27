package com.example.learnspring.postprocessor.bean;

import com.example.learnspring.postprocessor.mapper.Mapper1;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Config
 * @Author: lhb
 * @Date: 2022/3/27 14:49
 */

@Configuration
@ComponentScan(value = "com.example.learnspring.postprocessor.bean")
public class Config {

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public Bean3 bean3() {
        return new Bean3();
    }
}
