package com.percent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 10:40
 * @Description:
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Employee {

    private Integer id;
    private Integer age;
    private BigDecimal salary;
}
