package org.example;

import org.example.service.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 启动后自动执行
    @Bean
    public CommandLineRunner run(HelloService helloService) {
        return args -> {
            String result = helloService.greet("张三");
            System.out.println(result);
        };
    }
}