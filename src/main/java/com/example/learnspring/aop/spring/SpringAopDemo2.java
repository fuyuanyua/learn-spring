package com.example.learnspring.aop.spring;

import com.example.learnspring.aop.Chinese;
import com.example.learnspring.aop.Man;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @Description: Spring中advisor实现AOP示例
 * @Author: lhb
 * @Date: 2022/3/24 22:53
 *
 *      切面的概念有两种解释：
 *          1.高级切面：aspect = advice1 + pointcut1
 *                             advice2 + pointcut2
 *                             ...
 *          2.底层切面：advisor = advice + pointcut
 *
 *          可以看出，aspect包含多组通知+切点，而advisor只包含一组通知+切点，粒度更细
 *          @Aspect 是Spring提供给开发者实现AOP的API，适合编程使用
 *          advisor则在Spring框架内部使用，我们的@Aspect最终会被框架转化成多组advisor，由advisor完成最终的AOP实现
 *
 *      整体流程：
 *          1.准备一个pointcut（切点）
 *          2.准备一个advice（通知）
 *          3.将pointcut和advice封装成一个advisor（切面）
 *          4.准备一个proxyFactory（代理工厂），传入【被代理对象】和【切面】，返回一个【代理对象】
 *
 *      Spring选择jdk还是cglib完成动态代理？
 *          1.proxyTargetClass == false && 被代理的类实现了接口 -> jdk
 *          2.proxyTargetClass == false && 被代理的类未实现接口 -> cglib
 *          3.proxyTargetClass == true -> cglib
 *
 *      proxyTargetClass这个值在哪设置？怎么设置的？
 *          在SpringBoot中，Aop自动配置类：AopAutoConfiguration，根据配置文件的条件设置proxyTargetClass是true还是false
 *          配置文件：【spring.aop.proxy-target-class】=【true】or【false】，默认为【true】，表示代理对象都默认由【cglib】来创建
 *
 */

@Slf4j
public class SpringAopDemo2 {

    public static void main(String[] args) {

        // 1.准备切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 切点表达式，execution匹配方法路径、类、方法名、方法参数、方法返回类型等，annotation匹配方法上的注解
        pointcut.setExpression("execution(* com.example.learnspring.aop..*.eat(..))");

        // 1.1 自己实现一个切点，模拟@Transactional注解是如何匹配的
        // @Transactional表示开启事务，是spring aop的一个具体实现，注解可以加载方法、类或接口上
        StaticMethodMatcherPointcut pointcut1 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 检查方法上有没有@Transactional注解
                MergedAnnotations fromMethod = MergedAnnotations.from(method);
                if (fromMethod.isPresent(Transactional.class)) {
                    return true;
                }
                // 检查类的整颗继承树上（包括类本身、超类、接口）有没有@Transactional注解
                MergedAnnotations fromClass = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                if (fromClass.isPresent(Transactional.class)) {
                    return true;
                }
                return false;
            }
        };

        // 2.准备通知
        MethodInterceptor advice = new MethodInterceptor() {

            /**
             *
             * @param invocation 调用链：递归调用所有通知
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                log.info("before");
                // 调用下一个通知
                Object result = invocation.proceed();
                log.info("after");
                return result;
            }
        };

        // 3.准备切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        // 4.创建代理对象
        // 4.1 代理工厂，用于创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory();
        // 4.2 设置被代理的对象
        proxyFactory.setTarget(new Chinese());
        proxyFactory.setInterfaces(Chinese.class.getInterfaces());
        // 4.3 设置切面
        proxyFactory.addAdvisor(advisor);
        // 4.4 生成代理对象
        Man proxy = (Man) proxyFactory.getProxy();

        // eat方法被增强
        proxy.eat();
        // sleep方法未被增强
        proxy.sleep(1);

        // 测试是由jdk还是cglib实现动态代理
        // 结果为：com.sun.proxy.$Proxy0，表示是jdk实现的动态代理
        Class<? extends Man> clazz = proxy.getClass();
        log.info("代理类：{}", clazz);
    }
}