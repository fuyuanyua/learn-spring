package com.example.learnspring.aop.spring;

import com.example.learnspring.entity.Chinese;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: @Aspect -> advisor的解析过程示例
 * @Author: lhb
 * @Date: 2022/3/25 12:40
 *
 *      1.那么Spring框架是如何将@Aspect解析成多组advisor呢？
 *          通过AnnotationAwareAspectJAutoProxyCreator（BeanPostProcessor）实现
 *          以下提供模拟解析过程示例（提供大致思路，非Spring源码）
 *
 *      2.所有类型的advisor最终都要被转化成MethodInterceptor【适配器模式】
 *          1.前置通知：AspectJMethodBeforeAdvice -> MethodBeforeAdviceInterceptor
 *          2.异常通知：ThrowsAdvice -> ThrowsAdviceInterceptor
 *          3.返回通知：AfterReturningAdvice -> AfterReturningAdviceInterceptor
 *          4.环绕通知：无需转换，本身已实现MethodInterceptor接口
 *          5.后置通知：无需转换，本身已实现MethodInterceptor接口
 */

@Slf4j
public class SpringAopDemo3 {

    // 为方便示例，都用内部类来实现

    /**
     * 准备一个切面类，定义了多组通知和切点
     */
    @Aspect
    static class MyAspect {

        @Before("execution(* com.example.learnspring.aop..*.eat(..))")
        public void before1() {
            log.info("执行前置通知1");
        }

        @Before("execution(* com.example.learnspring.aop..*.eat(..))")
        public void before2() {
            log.info("执行前置通知2");
        }

        @After("execution(* com.example.learnspring.aop..*.eat(..))")
        public void after1() {
            log.info("执行后置通知1");
        }

        @Around("execution(* com.example.learnspring.aop..*.eat(..))")
        public Object around1(ProceedingJoinPoint pjp) {
            log.info("执行环绕通知1");
            return null;
        }
    }


    public static void main(String[] args) throws NoSuchMethodException {
        List<Advisor> advisors = new ArrayList<>();
        // 切面工厂
        SingletonAspectInstanceFactory factory = new SingletonAspectInstanceFactory(new MyAspect());
        // 假设AnnotationAwareAspectJAutoProxyCreator已经帮我们扫描到了所有@Aspect的切面类，那么开始解析切面类
        for (Method method : MyAspect.class.getDeclaredMethods()) {
            // 解析前置通知
            if (method.isAnnotationPresent(Before.class)) {
                // 1.获取切点表达式，封装到pointcut
                String expression = method.getAnnotation(Before.class).value();
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expression);

                // 2.准备通知
                AspectJMethodBeforeAdvice beforeAdvice = new AspectJMethodBeforeAdvice(method, pointcut, factory);

                // 3.将切点和通知封装成切面
                DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, beforeAdvice);
                advisors.add(advisor);
            }

            // 解析后置通知
            else if (method.isAnnotationPresent(After.class)) {
                // 1.获取切点表达式，封装到pointcut
                String expression = method.getAnnotation(After.class).value();
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expression);

                // 2.准备通知
                AspectJAfterAdvice afterAdvice = new AspectJAfterAdvice(method, pointcut, factory);

                // 3.将切点和通知封装成切面
                DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, afterAdvice);
                advisors.add(advisor);
            }

            // 解析环绕通知
            else if (method.isAnnotationPresent(Around.class)) {
                // 1.获取切点表达式，封装到pointcut
                String expression = method.getAnnotation(Around.class).value();
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expression);

                // 2.准备通知
                AspectJAroundAdvice aroundAdvice = new AspectJAroundAdvice(method, pointcut, factory);

                // 3.将切点和通知封装成切面
                DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, aroundAdvice);
                advisors.add(advisor);
            }
        }

        for (Advisor advisor : advisors) {
            log.info("{}", advisor);
        }

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Chinese());
        proxyFactory.setInterfaces(Chinese.class.getInterfaces());
        proxyFactory.addAdvisors(advisors);

        /*
            适配器模式：
                我们解析@Before获取到的通知是AspectJMethodBeforeAdvice，但是需要转化成MethodInterceptor
            适配过程：
                MethodBeforeAdviceAdapter：将AspectJMethodBeforeAdvice -> MethodBeforeAdviceInterceptor
                ...
         */
        List<Object> interceptors = proxyFactory.getInterceptorsAndDynamicInterceptionAdvice(Chinese.class.getMethod("eat"), Chinese.class);
        log.info("适配后");
        for (Object interceptor : interceptors) {
            log.info("{}", interceptor);
        }
    }
}
