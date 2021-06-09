package com.planning.springbean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yxc
 * @date 2021/6/10 7:29 上午
 */
@Data
@Component
@AllArgsConstructor
public class Company {

    private Address address;
}
