package com.example.learnspring.aop.cglib;

import com.example.learnspring.aop.Chinese;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: cglib动态代理实例
 * @Author: lhb
 * @Date: 2022/3/24 17:33
 *
 *      cglib动态代理既可以针对接口、也可以针对类进行代理
 *      cglib使用asm动态生成代理类的.class文件到内存中：具体可看源码：Enhancer.generateClass方法
 */

@Slf4j
public class CglibDemo {
    public static void main(String[] args) {

        // 被代理的对象
        Chinese chinese = new Chinese();
        // 生成代理对象
        Chinese chineseProxy = (Chinese) Enhancer.create(Chinese.class, new MethodInterceptor() {

            /**
             *
             * @param o 代理对象本身
             * @param method 代理对象正在执行的方法
             * @param objects 代理对象正在执行的方法的所有参数
             * @param methodProxy
             * @return 代理对象正在执行的方法的返回值
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // AOP：前置通知
                log.info("before invoke");
                // 执行方法本身
                Object invoke = method.invoke(chinese, args);
                // AOP：后置通知
                log.info("after invoke");
                return invoke;
            }
        });
        chineseProxy.eat();
    }
}
