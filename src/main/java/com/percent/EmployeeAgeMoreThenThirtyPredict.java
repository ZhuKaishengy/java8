package com.percent;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-5 10:59
 * @Description:
 */
public class EmployeeAgeMoreThenThirtyPredict implements Predict<Employee> {

    @Override
    public boolean match(Employee employee) {
        return employee.getAge() > 30;
    }
}
