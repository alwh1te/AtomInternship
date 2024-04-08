package org.example.atom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class AtomApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomApplication.class, args);
    }
}
