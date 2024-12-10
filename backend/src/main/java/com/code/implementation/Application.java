package com.code.implementation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.web.server.WebServer;
// import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Value("${server.port}")
    private int serverPort; // รับค่าพอร์ตจาก application.properties

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Server started at http://localhost:" + serverPort);
        System.out.println("WebGL Server started at http://localhost:8080");
        System.out.println("=".repeat(50) + "\n");
    }
}