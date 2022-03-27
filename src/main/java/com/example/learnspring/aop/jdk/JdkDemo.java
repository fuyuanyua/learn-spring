package com.example.learnspring.aop.jdk;

import com.example.learnspring.mapper.CarMapper;
import com.example.learnspring.entity.Chinese;
import com.example.learnspring.entity.Man;
import com.example.learnspring.entity.Car;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

/**
 * @Description: jdk动态代理实例
 * @Author: lhb
 * @Date: 2022/3/24 16:10
 *
 *      jdk动态代理只能针对接口进行代理
 */

@Slf4j
public class JdkDemo {

    public static void main(String[] args) {

        // 被代理的对象
        Man target = new Chinese();
        // 生成代理对象
        // claasLoader用来加载在运行时动态生成的字节码文件
        Man proxyInstance = (Man) Proxy.newProxyInstance(Man.class.getClassLoader(), new Class[]{Man.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // AOP：前置通知
                log.info("before invoke");
                // 执行方法本身
                Object invoke = method.invoke(target, args);
                // AOP：后置通知
                log.info("after invoke");
                return invoke;
            }
        });
        proxyInstance.eat();
        proxyInstance.sleep(1);


        CarMapper carMapperProxy = (CarMapper) Proxy.newProxyInstance(CarMapper.class.getClassLoader(), new Class[]{CarMapper.class}, new InvocationHandler() {

            /**
             *
             * @param proxy 代理对象本身
             * @param method 代理对象正在执行的方法
             * @param args 代理对象正在执行的方法的所有参数
             * @return 代理对象正在执行的方法的返回值
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("参数：{}", args);
                log.info("执行sql");
                if (method.getName().equals("getCar")) {
                    return new Car("bmw", "blue");
                }
                return Collections.emptyList();
            }
        });
        Car car = carMapperProxy.getCar(1);
        List<Car> bwm = carMapperProxy.listCar("bwm");
    }
}
