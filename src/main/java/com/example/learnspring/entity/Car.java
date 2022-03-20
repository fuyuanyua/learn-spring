package com.example.learnspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: Car
 * @Author: lhb
 * @Date: 2022/3/19 20:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String brand;

    private String color;
}
