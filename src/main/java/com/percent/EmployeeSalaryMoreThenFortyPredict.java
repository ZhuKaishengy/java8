package com.percent;

import java.math.BigDecimal;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 11:04
 * @Description:
 */
public class EmployeeSalaryMoreThenFortyPredict implements Predict<Employee> {

    @Override
    public boolean match(Employee employee) {
        return employee.getSalary().compareTo(BigDecimal.valueOf(4000)) > 0;
    }
}
