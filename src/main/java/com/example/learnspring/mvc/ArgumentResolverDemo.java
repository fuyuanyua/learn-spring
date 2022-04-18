package com.example.learnspring.mvc;

import com.example.learnspring.entity.Car;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 请求参数解析
 * @Author: lhb
 * @Date: 2022/4/17 21:53
 */

public class ArgumentResolverDemo {

    public static void main(String[] args) {

    }

    @RestController
    @RequestMapping("/test")
    public static class TestController {

        @RequestMapping("/test1")
        public String test1(
                // RequestParamMethodArgumentResolver
                // ...test/test1/name1=xxx
                @RequestParam("name") String name1,

                // RequestParamMethodArgumentResolver
                // ...test/test1/name2=xxx
                String name2,

                // RequestParamMethodArgumentResolver
                // ...test/test1/age=xxx
                @RequestParam("age") int age,

                // RequestParamMethodArgumentResolver
                // 文件上传
                @RequestParam("file") MultipartFile file,

                // PathVariableMethodArgumentResolver
                // ...test/test1/{id}
                @PathVariable("id") int id,

                // RequestHeaderMethodArgumentResolver
                // 请求头Content-Type中获取
                @RequestHeader("Content-Type") String contentType,

                // ServletCookieValueMethodArgumentResolver
                // 请求头cookie中获取
                @CookieValue("token") String token,

                // ServletRequestMethodArgumentResolver
                HttpServletRequest request,

                // ServletModelAttributeMethodProcessor，指定必须带有@ModelAttribute注解，并把解析结果存入ModelAndViewContainer
                // ...test/test1/brand=xxx&color=xxx
                @ModelAttribute Car car1,

                // ServletModelAttributeMethodProcessor，指定无需带有@ModelAttribute注解，并把解析结果存入ModelAndViewContainer
                // ...test/test1/brand=xxx&color=xxx
                Car car2,

                // RequestResponseBodyMethodProcessor：除了解析 @RequestBody 的请求参数，同样也解析 @ResponseBody 返回值
                // 请求体中获取
                @RequestBody Car car3
        ) {
            // do something
            return "success";
        }
    }
}
