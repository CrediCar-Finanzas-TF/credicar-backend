package com.credicar.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class CredicarBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CredicarBackendApplication.class, args);
    }

}
