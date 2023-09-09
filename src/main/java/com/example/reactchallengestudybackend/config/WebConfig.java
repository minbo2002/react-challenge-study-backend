package com.example.reactchallengestudybackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
    CORS(교차 리소스 공유) : 정책때문에 리액트 앱이 스프링부트 프로젝트로 접근을 못하므로
                          스프링부트 프로젝트내에서 CORS 설정.
                          리액트에서는 개발서버에서만 CORS 적용가능하고 운영서버에서는 적용못하므로 스프링부트 프로젝트에서 CORS 설정 해줘야됨.
    */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")              // 모든 경로를 허용
                .allowedOrigins("http://localhost:3000")  // 특정 도메인 주소만 허용
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name()

                );
    }
}
