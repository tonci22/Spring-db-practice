package com.example.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "accommodation")
public class AccommodationOwner {

    private String ownerValue;
    private Owner owner = new Owner();
    @Data
    public static class Owner {

        private String name;
        private String facebook;
        private String instagram;
    }
}
