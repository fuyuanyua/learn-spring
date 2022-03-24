package com.example.learnspring.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Chinese
 * @Author: lhb
 * @Date: 2022/3/24 16:47
 */

@Slf4j
public class Chinese implements Man {
    @Override
    public void eat() {
        log.info("吃饭");
    }

    @Override
    public void sleep(int hour) {
        log.info("睡觉{}小时", hour);
    }
}
