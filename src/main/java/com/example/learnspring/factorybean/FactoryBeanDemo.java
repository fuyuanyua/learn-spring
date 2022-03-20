package com.example.learnspring.factorybean;

import com.example.learnspring.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: FactoryBean示例
 * @Author: lhb
 * @Date: 2022/3/20 21:47
 *
 * FactoryBean：
 *      是一个接口，如果一个bean实现了这个接口，那么它就成为一个特殊的bean，它被用作一个对象的工厂来暴露，而不是直接作为一个将自己暴露的bean实例。
 *      也就是说，从容器中获取此bean，返回的将不是它的实例本身，而是它实现的getObject方法中返回的对象（这个对象可以用任意方式来实例化）
 *
 *      Q：FactoryBean和BeanFactory什么区别？
 *      A：无意义问题
 */

@Slf4j
public class FactoryBeanDemo implements FactoryBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(FactoryBeanDemo.class);
        ctx.refresh();
        // 获取的将不是bean本身，而是它实现的getObject方法中返回的对象
        Object bean1 = ctx.getBean("factoryBeanDemo");
        log.info("bean is {}, class is {}", bean1, bean1.getClass());
        // 想要获取bean本身也可以，bean名字前面需要加上&
        Object bean2 = ctx.getBean("&factoryBeanDemo");
        log.info("bean is {}, class is {}", bean2, bean2.getClass());
        String[] beanNamesForType = ctx.getBeanNamesForType(FactoryBeanDemo.class);
        for (String s : beanNamesForType) {
            log.info("{}", s);
        }
    }

    @Override
    public Object getObject() throws Exception {
        Car car = new Car("bmw", "black");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }
}
