package com.example.learnspring.aop.spring;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @Description: SpringAopDemo4
 * @Author: lhb
 * @Date: 2022/3/25 16:41
 *
 *      1.SpringAopDemo3中已将所有适配好的通知（MethodInterceptor）放到了一个集合中，那么应该按什么步骤去执行所有这些通知呢？
 *          封装一个调用链，也就是MethodInvocation，让调用链去执行
 *          以下提供模拟过程示例：自定义一个调用链，执行所有通知（提供大致思路，非Spring源码）
 *
 *      2.以上说的MethodInvocation调用链，其实就是采用了【责任链模式】
 *
 */

@Slf4j
public class SpringAopDemo5 {

    /**
     * 自定义的调用链
     */
    static class MyMethodInvocation implements MethodInvocation {

        @Override
        public Method getMethod() {
            return null;
        }

        @Override
        public Object[] getArguments() {
            return new Object[0];
        }

        @Override
        public Object proceed() throws Throwable {
            return null;
        }

        @Override
        public Object getThis() {
            return null;
        }

        @Override
        public AccessibleObject getStaticPart() {
            return null;
        }
    }

    public static void main(String[] args) {

        // 定义advice1
        MethodInterceptor advice1 = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                log.info("before1");
                Object proceed = invocation.proceed();
                log.info("after1");
                return proceed;
            }
        };

        // 定义advice2
        MethodInterceptor advice2 = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                log.info("before2");
                Object proceed = invocation.proceed();
                log.info("after2");
                return proceed;
            }
        };



    }
}
