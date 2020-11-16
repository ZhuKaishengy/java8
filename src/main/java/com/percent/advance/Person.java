package com.percent.advance;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author : zhukaishengy
 * @date : 2020/11/16 10:22
 * @Description : BO
 * @version : v1.0
 */
@SuperBuilder
@Data
@NoArgsConstructor
public class Person {
    private int id;
    private String name;
}
