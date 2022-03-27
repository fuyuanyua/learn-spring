package com.example.learnspring.postprocessor.beanpostprocessor;

import com.example.learnspring.postprocessor.bean.Bean1;
import com.example.learnspring.postprocessor.bean.Bean2;
import com.example.learnspring.postprocessor.bean.Bean3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: 常见的BeanPostProcessor示例
 * @Author: lhb
 * @Date: 2022/3/27 13:56
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
