package com.example.demo;

import com.example.demo.configuration.AccommodationOwner;
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
public class ApplicationStart {

    private final AccommodationService accommodationService;
    private final LocationService locationService;
	private final AccommodationOwner accommodationOwner;

    public ApplicationStart(@Qualifier("accommodationServiceImpl") final AccommodationService accommodationService,
                            @Qualifier("locationServiceImpl") LocationService locationService, AccommodationOwner accommodationOwner) {
        this.accommodationService = accommodationService;
        this.locationService = locationService;
		this.accommodationOwner = accommodationOwner;
	}

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

    @PostConstruct
    public void initData() {

        LocationRequestDto location = new LocationRequestDto();
        location.setPostalCode(20263);
        location.setTitle("Lumbarda");
        location.setSubtitle("jadno lipo misto");
        location.setId(1L);
        locationService.add(location);

        AccommodationRequestDto accommodation = new AccommodationRequestDto();
        accommodation.setTitle("titl");
        accommodation.setSubtitle("subtitl");
        accommodation.setCategorization(1);
        accommodation.setFreeCancellation(false);
        accommodation.setImage(new Byte[]{2, 31, 55, 99});
        accommodation.setPrice(9999);
        accommodation.setPersonCount(12);
        accommodation.setType(AccommodationType.APARTMENT);
        accommodation.setLocation(location);

        accommodationService.add(accommodation);

		System.out.println(accommodationOwner);
    }
}
