package com.example.learnspring.circulardependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: 循环依赖示例
 * @Author: lhb
 * @Date: 2022/4/9 22:17
 */

@Slf4j
public class CircularDependencyDemo {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();

        ctx.registerBean("a", A.class);
        ctx.registerBean("b", B.class);

        // 处理【@Autowired】、【@Value】注解
        ctx.registerBean(AutowiredAnnotationBeanPostProcessor.class);

        ctx.refresh();
    }

    static class A {

        private B b;

        @Autowired
        public void setB(B b) {
            this.b = b;
            log.info("a：依赖注入b：{}", b.getClass());
        }

        public A() {
            log.info("a：构造方法");
        }
    }

    static class B {

        private A a;

        @Autowired
        public void setA(A a) {
            this.a = a;
            log.info("b：依赖注入a：{}", a.getClass());
        }

        public B() {
            log.info("b：构造方法");
        }
    }
}
