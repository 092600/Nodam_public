package com.nodam.nodam_public;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NodamPublicApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodamPublicApplication.class, args);
    }

}
