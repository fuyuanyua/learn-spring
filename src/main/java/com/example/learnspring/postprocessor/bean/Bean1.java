package com.example.learnspring.postprocessor.bean;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @Description: Bean1
 * @Author: lhb
 * @Date: 2022/3/27 13:56
 */

@Slf4j
@ToString
public class Bean1 implements BeanNameAware {

    private Bean2 bean2;

    private Bean3 bean3;

    private String beanName;

    @Autowired
    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
        log.info("@Autowired生效");
    }

    @Resource(name = "bean3")
    public void setBean3(Bean3 bean3) {
        this.bean3 = bean3;
        log.info("@Resource生效");
    }

    @PostConstruct
    public void init() {
        log.info("@PostConstruct生效");
    }

    @PreDestroy
    public void destroy() {
        log.info("@PreDestroy生效");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        log.info("调用BeanNameAware");
    }
}
