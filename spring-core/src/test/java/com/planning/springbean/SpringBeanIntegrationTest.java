package com.planning.springbean;

import com.planning.springbean.domain.Company;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

/**
 * @author yxc
 * @date 2021/6/10 7:38 上午
 */
public class SpringBeanIntegrationTest {

    @Test
    public void whenUsingIoC_thenDependenciesAreInjected() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Company company = context.getBean("company", Company.class);
        assertEquals("High Street", company.getAddress().getStreet());
        assertEquals(1000, company.getAddress().getNumber());
    }
}
