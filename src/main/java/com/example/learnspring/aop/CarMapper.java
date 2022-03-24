package com.example.learnspring.aop;

import com.example.learnspring.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: CarMapper
 * @Author: lhb
 * @Date: 2022/3/24 17:17
 */

@Mapper
public interface CarMapper {

    Car getCar(int id);

    List<Car> listCar(String name);
}
