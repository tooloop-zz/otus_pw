package ru.otus.asamofalov.pw.srv03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.otus.asamofalov")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
