package com.example.demo;

import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.enums.AccommodationType;
import com.example.demo.service.AccommodationService;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

private final AccommodationService accommodationService;
private final LocationService locationService;

	public DemoApplication(@Qualifier("accommodationServiceImpl") final AccommodationService accommodationService,
						   @Qualifier("locationServiceImpl") LocationService locationService) {
		this.accommodationService = accommodationService;
		this.locationService = locationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void initData(){

		LocationRequestDto location = new LocationRequestDto();
		location.setPostalCode(20263);
		location.setTitle("Lumbarda");
		location.setId(1L);
		locationService.add(location);

		AccommodationRequestDto accommodation = new AccommodationRequestDto();
		accommodation.setCategorization(1);
		accommodation.setFreeCancelation(false);
		accommodation.setImage(new Byte[]{2,31,55,99});
		accommodation.setPrice(9999);
		accommodation.setPersonCount(12);
		accommodation.setType(AccommodationType.APARTMENT);
		accommodation.setLocation(location);

		accommodationService.add(accommodation);
	}
}
