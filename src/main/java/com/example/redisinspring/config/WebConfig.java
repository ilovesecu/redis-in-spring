package com.example.redisinspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:3000", "https://sync.ilovepc.duckdns.org")
                .allowedMethods("*")
                .allowCredentials(true) //쿠키 인증요청 허용
                .maxAge(3000); //원하는 시간만큼 pre-flight 리퀘스트 캐싱
    }
}
