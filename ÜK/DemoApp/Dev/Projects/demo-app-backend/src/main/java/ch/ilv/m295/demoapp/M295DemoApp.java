package ch.ilv.m295.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class M295DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(M295DemoApp.class, args);
    }
}
