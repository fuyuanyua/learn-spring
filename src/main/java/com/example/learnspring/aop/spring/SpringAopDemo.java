package com.example.learnspring.aop.spring;

import com.example.learnspring.aop.Chinese;
import com.example.learnspring.aop.Man;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Description: Spring中的AOP示例
 * @Author: lhb
 * @Date: 2022/3/24 22:53
 *
 *      切面的概念有两种解释：
 *          1.aspect = advice1 + pointcut1
 *                     advice2 + pointcut2
 *                     ...
 *          2.advisor = advice + pointcut
 *
 *          可以看出，aspect包含多组通知+切点，而advisor只包含一组通知+切点，粒度更细
 *
 *      Spring选择jdk还是cglib完成动态代理？
 *          1.proxyTargetClass == false && 被代理的类实现了接口 -> jdk
 *          2.proxyTargetClass == false && 被代理的类未实现接口 -> cglib
 *          3.proxyTargetClass == true -> cglib
 */

@Slf4j
public class SpringAopDemo {

    public static void main(String[] args) {

        // 1.准备切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.example.learnspring.aop..*.eat(..))");

        // 2.准备通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                log.info("before invoke");
                Object result = invocation.proceed();
                log.info("after invoke");
                return result;
            }
        };

        // 3.准备切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        // 4.创建代理对象
        // 被代理的对象
        Chinese chinese = new Chinese();
        // 代理工厂，用于创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(chinese);
        // 告诉代理工厂被代理的类实现了哪些接口
        proxyFactory.setInterfaces(chinese.getClass().getInterfaces());
        proxyFactory.addAdvisor(advisor);
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
