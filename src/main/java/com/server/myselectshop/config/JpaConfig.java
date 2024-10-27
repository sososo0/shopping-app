package com.server.myselectshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // 시간 자동 변경이 가능하도록 한다.
public class JpaConfig {
}
