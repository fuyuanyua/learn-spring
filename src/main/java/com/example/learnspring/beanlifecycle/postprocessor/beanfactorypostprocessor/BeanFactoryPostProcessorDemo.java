package com.example.learnspring.beanlifecycle.postprocessor.beanfactorypostprocessor;

import com.example.learnspring.beanlifecycle.postprocessor.config.Config;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: 常见的BeanFactoryPostProcessor示例
 * @Author: lhb
 * @Date: 2022/3/27 14:48
 */

public class BeanFactoryPostProcessorDemo {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();

        ctx.registerBean(Config.class);
        // 处理【@Bean】、【@ComponentScan】、【Import】等注解
        // 例如【@ComponentScan】，就是扫描路径下符合条件的组件，封装成【BeanDefinition】并添加到容器中
        ctx.registerBean(ConfigurationClassPostProcessor.class);
        // MyBatis中用来扫描【@Mapper】，封装成【MapperFactoryBean】类型的【BeanDefinition】并添加到容器中
        ctx.registerBean(MapperScannerConfigurer.class, bd -> {
            bd.getPropertyValues().add("basePackage", "com.example.learnspring.postprocessor.mapper");
        });

        ctx.refresh();
    }
}
