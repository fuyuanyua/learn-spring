package com.example.learnspring.aop.spring;

import com.example.learnspring.aop.Chinese;
import com.example.learnspring.aop.Man;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: Spring中@Aspect注解实现AOP示例
 * @Author: lhb
 * @Date: 2022/3/25 12:44
 *
 *      @Aspect 是Spring框架提供给开发者的API，开发者通过@Aspect注解完成AOP的实现，以下提供示例
 *
 *      Spring AOP的一些特点：
 *          1.代理对象的属性和原始对象的属性不共用，所以直接调用【代理对象.属性】可能获取不到值，
 *            但是调用【代理对象.get属性】可以取到值，且是原始对象的值，因为代理对象里封装了原始对象，
 *            这样实际上是调用【原始对象.get属性】
 *          2.基于动态代理，【static】、【final】、【private】方法均无法被增强
 */

@Slf4j
public class SpringAopDemo1 {

    @Aspect
    static class MyAspect {

        @Before("execution(* com.example.learnspring.aop..*.eat(..))")
        public void before() {
            log.info("before");
        }
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();
        ctx.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);
        ctx.registerBean("myAspect", MyAspect.class);
        ctx.registerBean("chinese", Chinese.class);
        ctx.refresh();

        // 获取到的是代理对象
        Man chinese = (Man) ctx.getBean("chinese");
        log.info("{}", chinese.getClass());
        // 方法被增强
        chinese.eat();
    }
}
