package by.mishelby.logistickapplication;

import by.mishelby.logistickapplication.mapper.DriverMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.DriverManager;

@SpringBootApplication
public class LogisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticApplication.class, args);
    }

}
