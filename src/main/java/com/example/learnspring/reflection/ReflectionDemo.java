package com.example.learnspring.reflection;

import com.example.learnspring.entity.Car;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * @Description: ReflectionDemo
 * @Author: lhb
 * @Date: 2022/3/19 20:45
 *
 * 反射：
 *      1.获取Class对象三种方法：
 *          1.Class.forName("全限定类名")
 *          2.类.class
 *          3.对象.getClass()
 *      2.获取Class对象的构造方法：
 *          clazz.getDeclaredConstructor()
 *      3.通过构造方法生成实例对象：
 *          constructor.newInstance()
 *
 *      Spring IOC容器大量使用了反射机制来实例化bean
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

        // 第二种获取Class对象的方法
        Class<Car> clazz1 = Car.class;

        // 第三种获取Class对象的方法
        Class<? extends Car> clazz2 = instance.getClass();
    }
}
