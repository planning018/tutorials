package com.planning.springbean;

import com.planning.springbean.domain.Address;
import com.planning.springbean.domain.Company;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yxc
 * @date 2021/6/10 7:30 上午
 */
@Configuration
@ComponentScan(basePackageClasses = Company.class)
public class Config {

    @Bean
    public Address getAddress() {
        return new Address("High Street", 1000);
    }
}
