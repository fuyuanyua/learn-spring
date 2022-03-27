package com.example.learnspring.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

/**
 * @Description: MyCondition
 * @Author: lhb
 * @Date: 2022/3/27 00:03
 */

public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取MyConditionalOnClass注解上标注的所有属性
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(MyConditionalOnClass.class.getName());
        // 获取注解上className的值
        String className = (String) annotationAttributes.get("className");
        // 判断是否存在这个类
        return ClassUtils.isPresent(className, null);
    }
}
