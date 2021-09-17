package com.planning.springcloudgateway.custompredicates.service;

import org.springframework.stereotype.Component;

/**
 * @author yxc
 * @date 2021/9/16 3:37 下午
 */
@Component
public class GoldenCustomerService {

    public boolean isGoldenCustomer(String customerId){
        // mock ; 可以调用 RPC 接口查询
        return "ymm".equalsIgnoreCase(customerId);
    }
}