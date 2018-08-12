package com.percent.streamexercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 11:39
 * @Description: 交易员
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
class Trader {

    /**
     * 交易员姓名
     */
    private String name;
    /**
     * 交易员所属城市
     */
    private String city;
}
