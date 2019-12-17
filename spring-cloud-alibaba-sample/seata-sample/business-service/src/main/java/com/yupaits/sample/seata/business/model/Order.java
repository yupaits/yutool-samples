package com.yupaits.sample.seata.business.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yupaits
 * @date 2019/8/28
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String userId;
    private String commodityCode;
    private int count;
    private int money;
}
