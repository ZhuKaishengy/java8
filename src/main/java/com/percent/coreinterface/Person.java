package com.percent.coreinterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-9 10:59
 * @Description: Person
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
class Person {
    /**
     * id
     */
    private String id;
    /**
     * name
     */
    private String name;
}
