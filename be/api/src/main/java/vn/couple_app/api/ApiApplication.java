package vn.couple_app.api;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootApplication
@EnableFeignClients
public class ApiApplication {

    @Autowired
    MessageSource messageSource;

	public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}