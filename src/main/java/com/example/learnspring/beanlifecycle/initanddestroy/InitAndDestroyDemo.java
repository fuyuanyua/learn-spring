package com.example.learnspring.beanlifecycle.initanddestroy;

import com.example.learnspring.beanlifecycle.initanddestroy.config.BeanConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description: bean生命周期之init和destroy
 * @Author: lhb
 * @Date: 2022/3/20 20:38
 *
 * bean生命周期：
 *      init：在bean初始化的时候调用【afterPropertiesSet】方法
 *      destroy：在bean销毁的时候调用【destroy】方法
 *
 *      有三种方式：
 *          1.通过JSR250规范定义的注解：@PostConstruct，@PreDestroy
 *          2.实现：InitializingBean接口、DisposableBean接口
 *          3.@Bean中指定：initMethod、destroyMethod
 *
 *      说明：三种方式选其一去实现即可
 */

@Slf4j
public class InitAndDestroyDemo implements InitializingBean, DisposableBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanConfiguration.class);
        ctx.refresh();
        ctx.close();
    }

    /**
     * 方式一：注解方式
     */
    @PostConstruct
    public void init1() {
        log.info("bean生命周期：init1");
    }

    /**
     * 方式一：注解方式
     */
    @PreDestroy
    public void destroy1() {
        log.info("bean生命周期：destroy1");
    }

    /**
     * 方式二：实现InitializingBean接口
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("bean生命周期：init2");
    }

    /**
     * 方式二：实现DisposableBean接口
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        log.info("bean生命周期：destroy2");
    }

    /**
     * 方式三：@Bean中指定initMethod
     */
    public void init3() {
        log.info("bean生命周期：init3");
    }

    /**
     * 方式三：@Bean中指定destroyMethod
     */
    public void destroy3() {
        log.info("bean生命周期：destroy3");
    }
}
