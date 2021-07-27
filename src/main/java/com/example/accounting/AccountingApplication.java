package com.example.accounting;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(
        scanBasePackages = {"com.example.accounting"}
)
@EnableVaadin({"com.example.accounting.view"})
public class AccountingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AccountingApplication.class, args);
    }

}
