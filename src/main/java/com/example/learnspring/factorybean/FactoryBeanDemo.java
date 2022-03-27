package com.example.learnspring.factorybean;

import com.example.learnspring.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: FactoryBean示例
 * @Author: lhb
 * @Date: 2022/3/20 21:47
 *
 * FactoryBean：
 *      是一个接口，如果一个bean实现了这个接口，那么它就成为了一个工厂bean，从容器中获取此bean将返回它生产出来的bean
 *      也就是说，从容器中获取此bean，返回的将不是它的实例本身，而是它实现的getObject方法中返回的对象（这个对象可以用任意方式来实例化）
 *
 *      缺点：
 *          1.工厂bean创建的bean，生命周期不完整，只有PostProcessor的after还可以生效【也就是AOP还是可以实现的】，其他生命周期不生效
 *          2.工厂bean创建的bean，不是缓存在一级缓存【singletonObjects】中，而是在【factoryBeanObjectCache】中
 *          3.按工厂名去容器取，拿到的是产品bean，想要获取工厂bean本身，工厂名前需要加上【&】
 *
 *      FactoryBean和BeanFactory什么区别？
 *          无意义问题，有一点要补充，MyBatis的mapper组件，就是通过实现FactoryBean创建的，也就是【MapperFactoryBean】
 */

@Slf4j
public class FactoryBeanDemo implements FactoryBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(FactoryBeanDemo.class);
        ctx.refresh();
        // 获取的将不是bean本身，而是它实现的getObject方法中返回的对象
        Object bean1 = ctx.getBean("factoryBeanDemo");
        log.info("bean is {}, class is {}", bean1, bean1.getClass());
        // 想要获取bean本身也可以，bean名字前面需要加上&
        Object bean2 = ctx.getBean("&factoryBeanDemo");
        log.info("bean is {}, class is {}", bean2, bean2.getClass());
        String[] beanNamesForType = ctx.getBeanNamesForType(FactoryBeanDemo.class);
        for (String s : beanNamesForType) {
            log.info("{}", s);
        }
    }

    @Override
    public Object getObject() throws Exception {
        Car car = new Car("bmw", "black");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }
}
