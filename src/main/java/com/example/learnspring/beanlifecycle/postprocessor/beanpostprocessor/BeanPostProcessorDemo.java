package com.example.learnspring.beanlifecycle.postprocessor.beanpostprocessor;

import com.example.learnspring.beanlifecycle.postprocessor.bean.Bean1;
import com.example.learnspring.beanlifecycle.postprocessor.bean.Bean2;
import com.example.learnspring.beanlifecycle.postprocessor.bean.Bean3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: 常见的BeanPostProcessor示例
 * @Author: lhb
 * @Date: 2022/3/27 13:56
 *
 *      bean生命周期：
 *          实例化 -> populateBean -> 调用Aware接口方法 -> before -> init -> after -> destroy
 *
 *      说明：
 *          一般来说，BeanPostProcessor在before和after调用
 *          也就是分别调用BeanPostProcessor的【postProcessBeforeInitialization】和【postProcessAfterInitialization】
 *          但是【InstantiationAwareBeanPostProcessor】类型的BeanPostProcessor，在【populateBean】中也会进行调用，
 *          【AutowiredAnnotationBeanPostProcessor】和【CommonAnnotationBeanPostProcessor】都实现了【InstantiationAwareBeanPostProcessor】，
 *          所以他们都会在【populateBean】中调用，也就是处理【@Autowired】、【@Resource】等，完成依赖注入
 *
 */

@Slf4j
public class BeanPostProcessorDemo {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new GenericApplicationContext();

        ctx.registerBean("bean1", Bean1.class);
        ctx.registerBean("bean2", Bean2.class);
        ctx.registerBean("bean3", Bean3.class);

        // 处理【@Autowired】、【@Value】注解
        ctx.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // 处理【@Resource】、【@PostConstruct】、【@PreDestroy】注解
        ctx.registerBean(CommonAnnotationBeanPostProcessor.class);

        // 初始化容器
        ctx.refresh();

        Object bean1 = ctx.getBean("bean1");
        log.info("{}", bean1);

        // 销毁容器
        ctx.close();
    }
}
