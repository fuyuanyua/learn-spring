package com.example.learnspring.aop.spring;

/**
 * @Description: AnnotationAwareAspectJAutoProxyCreator重要方法示例
 * @Author: lhb
 * @Date: 2022/3/25 17:22
 *
 *      SpringAopDemo3提到：@Aspect -> advisor的解析过程在Spring中真正是由AnnotationAwareAspectJAutoProxyCreator来完成
 *      关注AnnotationAwareAspectJAutoProxyCreator两个重要方法：
 *          1.findEligibleAdvisors：
 *              根据被代理的对象，从容器中找到所有有资格的advisor存到集合中
 *              advisor可以是原生advisor、也可以由@Aspect解析而来
 *          2.wrapIfNecessary：
 *              1.若findEligibleAdvisors获取到的advisor集合是空的，则不会创建代理对象，返回原生的对象
 *              2.若若findEligibleAdvisors获取到的advisor集合不为空，则通过proxyFactory使用jdk动态代理或cglib动态代理，返回代理对象
 */

public class SpringAopDemo4 {
}
