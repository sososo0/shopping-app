package com.server.myselectshop.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                // RestTemplate으로 외부 API 호출 시 일정 시간이 지나도 응답이 없을 떼
                // 무한 대기 상태를 방지하기 위해 강제 종료 설정
                .setConnectTimeout(Duration.ofSeconds(5)) // 5초
                .setReadTimeout(Duration.ofSeconds(5)) // 5초
                .build();
    }
}
