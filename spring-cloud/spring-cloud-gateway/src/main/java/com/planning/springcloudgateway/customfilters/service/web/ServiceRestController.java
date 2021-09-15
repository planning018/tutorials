package com.planning.springcloudgateway.customfilters.service.web;

import org.apache.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Locale;

/**
 * @author yxc
 * @date 2021/9/15 3:26 下午
 */
@RestController
@RequestMapping(value = "/service")
public class ServiceRestController {

    @GetMapping("/resource")
    public Mono<ResponseEntity<String>> getResource() {
        return Mono.just(ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_LANGUAGE, Locale.ENGLISH.getLanguage())
            .body("services Resource"));
    }
}
