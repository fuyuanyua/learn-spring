package com.example.learnspring.conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: MyConditionalOnClass
 * @Author: lhb
 * @Date: 2022/3/27 10:51
 */

@Target({ElementType.METHOD, ElementType.TYPE}) // 注解可作用于方法或类
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时生效
@Conditional(MyCondition.class)
public @interface MyConditionalOnClass {

    String className();
}
