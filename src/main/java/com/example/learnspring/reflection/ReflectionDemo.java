package com.example.learnspring.reflection;

import com.example.learnspring.entity.Car;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Description: 反射示例
 * @Author: lhb
 * @Date: 2022/3/19 20:45
 *
 * 反射：
 *      1.获取Class对象三种方法：
 *          1.Class.forName("全限定类名")
 *          2.类名.class
 *          3.对象.getClass()
 *      2.获取Class对象的构造方法：
 *          clazz.getDeclaredConstructor()
 *      3.通过构造方法生成实例对象：
 *          constructor.newInstance()
 *
 *      说明：Spring框架中大量使用了反射机制来实例化对象：
 *          1.bean的实例化
 *          2.SpringFactoriesLoader.loadFactories，加载spring.factories配置文件信息然后反射实例化对象
 *          3...
 */

@Slf4j
public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        // 第一种获取Class对象的方法
        Class<?> clazz = Class.forName("com.example.learnspring.entity.Car");
        // 获取构造方法
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        // 实例化对象
        Car instance = (Car) constructor.newInstance();
        instance.setBrand("bmw");
        log.info("{}", instance);
        // 通过反射获取getBrand方法
        Method method = clazz.getMethod("getBrand", null);
        // 获取方法入参
        Parameter[] parameters = method.getParameters();
        log.info("方法【{}】的入参【{}】", method, parameters);
        // 获取方法返回类型
        Class<?> returnType = method.getReturnType();
        log.info("方法【{}】的返回类型【{}】", method, returnType);
        // 通过反射调用getBrand方法
        Object invoke = method.invoke(instance, null);
        log.info("方法【{}】的执行结果【{}】", method, invoke);

        // 第二种获取Class对象的方法
        Class<Car> clazz1 = Car.class;

        // 第三种获取Class对象的方法
        Class<? extends Car> clazz2 = instance.getClass();
    }
}
