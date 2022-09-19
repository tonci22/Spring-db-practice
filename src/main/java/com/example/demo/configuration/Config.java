package com.example.demo.configuration;

import com.example.demo.service.Implementation.MobileHomeAccommodationServiceImpl;
import com.example.demo.service.Implementation.RoomAccommodationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RoomAccommodationServiceImpl roomAccommodationService(){
        return new RoomAccommodationServiceImpl();
    }

    @Bean
    public MobileHomeAccommodationServiceImpl mobileHomeAccommodationService(){
        return new MobileHomeAccommodationServiceImpl();
    }
}
