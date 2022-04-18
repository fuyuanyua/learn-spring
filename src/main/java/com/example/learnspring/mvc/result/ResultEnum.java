package com.example.learnspring.mvc.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: ResultEnum
 * @Author: lhb
 * @Date: 2020/11/16 23:14
 */

@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(20000, "成功"),
    ERROR(999, "错误");

    private Integer code;

    private String desc;
}
