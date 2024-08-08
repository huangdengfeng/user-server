package com.seezoon.user;

import com.seezoon.user.infrastructure.properties.AppProperties;
import com.seezoon.user.infrastructure.rpc.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;

@EnableTransactionManagement
@MapperScan(basePackages = "com.seezoon.user.domain.dao.mapper")
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@Slf4j
public class MainApplication implements CommandLineRunner {

    @Autowired
    private JwtService jwtService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String token = jwtService.sign("0", Duration.ofDays(365 * 10));
        log.info("system auth token {}", token);
    }
}
