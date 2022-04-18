package com.example.learnspring.controller;

import com.example.learnspring.entity.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: MyController
 * @Author: lhb
 * @Date: 2022/4/18 11:54
 */

@RestController
@RequestMapping("/my")
public class MyController {

    /**
     * 测试MyControllerAdvice
     * @return
     */
    @GetMapping("/test")
    public Car test() {
        return new Car("aa", "bb");
    }

    /**
     * 测试MyControllerAdvice1
     * @return
     */
    @GetMapping("/test1")
    public Car test1() {
        // 故意抛出一个异常
        int i = 1 / 0;
        return new Car("aa", "bb");
    }
}
