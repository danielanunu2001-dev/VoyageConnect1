package com.travelagency.voyageconnect1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Voyageconnect1Application {

    public static void main(String[] args) {
        SpringApplication.run(Voyageconnect1Application.class, args);
    }

}
