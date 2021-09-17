package com.planning.springcloudgateway.custompredicates.factories;

import com.planning.springcloudgateway.custompredicates.service.GoldenCustomerService;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpCookie;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author yxc
 * @date 2021/9/16 3:30 下午
 */
public class GoldenCustomerRoutePredicateFactory extends AbstractRoutePredicateFactory<GoldenCustomerRoutePredicateFactory.Config> {

    private final GoldenCustomerService goldenCustomerService;

    public GoldenCustomerRoutePredicateFactory(GoldenCustomerService goldenCustomerService) {
        super(Config.class);
        this.goldenCustomerService = goldenCustomerService;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("isGolden","customerIdCookie");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return (ServerWebExchange t) -> {
            List<HttpCookie> cookies = t.getRequest()
                    .getCookies()
                    .get(config.getCustomerIdCookie());
            boolean isGolden;
            if(CollectionUtils.isEmpty(cookies)){
                isGolden = false;
            }else {
                String customerId = cookies.get(0).getValue();
                isGolden = goldenCustomerService.isGoldenCustomer(customerId);
            }

            return config.isGolden == isGolden;
        };
    }

    public static class Config {
        boolean isGolden = true;

        @NotEmpty
        String customerIdCookie = "customerId";

        public Config() {
        }

        public Config(boolean isGolden, String customerIdCookie) {
            this.isGolden = isGolden;
            this.customerIdCookie = customerIdCookie;
        }

        public boolean isGolden() {
            return isGolden;
        }

        public void setGolden(boolean golden) {
            isGolden = golden;
        }

        public String getCustomerIdCookie() {
            return customerIdCookie;
        }

        public void setCustomerIdCookie(String customerIdCookie) {
            this.customerIdCookie = customerIdCookie;
        }
    }
}
