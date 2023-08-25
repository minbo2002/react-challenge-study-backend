package com.example.reactchallengestudybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReactChallengeStudyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactChallengeStudyBackendApplication.class, args);
    }

}
