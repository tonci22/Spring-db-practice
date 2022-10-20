package com.example.demo;

import com.example.demo.configuration.AccommodationOwner;
import com.example.demo.dto.request.AccommodationRequestDto;
import com.example.demo.dto.request.LocationRequestDto;
import com.example.demo.dto.request.ReservationRequestDto;
import com.example.demo.enums.AccommodationType;
import com.example.demo.enums.ReservationType;
import com.example.demo.service.AccommodationService;
import com.example.demo.service.LocationService;
import com.example.demo.service.ReservationService;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class ApplicationStart {

    private final AccommodationService accommodationService;
    private final LocationService locationService;
	private final AccommodationOwner accommodationOwner;
    private final ReservationService reservationService;
    private static final Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

    public ApplicationStart(@Qualifier("accommodationServiceImpl") final AccommodationService accommodationService,
                            @Qualifier("locationServiceImpl") final LocationService locationService,
                            final AccommodationOwner accommodationOwner,
                            @Qualifier("reservationServiceImpl") final ReservationService reservationService) {

        this.accommodationService = accommodationService;
        this.locationService = locationService;
		this.accommodationOwner = accommodationOwner;
        this.reservationService = reservationService;
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
        accommodation.setId(1L);
        accommodation.setTitle("titl");
        accommodation.setSubtitle("subtitl");
        accommodation.setCategorization(3);
        accommodation.setFreeCancellation(false);
        accommodation.setImage(new Byte[]{2, 31, 55, 99});
        accommodation.setPrice(9999);
        accommodation.setPersonCount(6);
        accommodation.setType(AccommodationType.APARTMENT);
        accommodation.setLocation(location);

        accommodationService.add(accommodation);

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto();
        reservationRequestDto.setAccommodation(accommodation);
        reservationRequestDto.setReservationType(ReservationType.TEST);
        reservationRequestDto.setSubmitted(true);
        reservationRequestDto.setCheckIn(new Timestamp(new Date().getTime() - 2342734342L));
        reservationRequestDto.setCheckOut(new Timestamp(new Date().getTime()));
        reservationRequestDto.setPersonsCount(3);

        reservationService.add(reservationRequestDto);

		System.out.println(accommodationOwner);
        accommodationService.getCategorizationAndMinimumBeds(3, 5).forEach(System.out::println);
        logger.debug("debug string");
    }
}
