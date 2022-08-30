package com.planning.springbean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yxc
 * @date 2021/6/10 7:29 上午
 */
@Data
@AllArgsConstructor
public class Address {
    private String street;
    private int number;
}
