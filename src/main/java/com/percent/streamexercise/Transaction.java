package com.percent.streamexercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author: kaisheng.zhu
 * @Date: 2018-8-12 11:40
 * @Description: 交易
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
class Transaction {

    /**
     * 交易员
     */
    private Trader trader;
    /**
     * 年
     */
    private Integer year;
    /**
     * 金额
     */
    private Double value;
}
