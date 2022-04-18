package com.example.learnspring.mvc.advice;

import com.example.learnspring.mvc.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 统一异常处理
 * @Author: lhb
 * @Date: 2022/4/18 12:44
 */

@ControllerAdvice
public class MyControllerAdvice1 {

    @ExceptionHandler
    @ResponseBody
    public Result handler(Exception e) {
        if (e instanceof RuntimeException) {
            return Result.error(e.getMessage());
        }
        return Result.error("其他异常");
    }
}
