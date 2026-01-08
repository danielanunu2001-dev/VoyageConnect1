package com.travelagency.voyageconnect1.web;

import com.travelagency.voyageconnect1.domain.dto.CircuitDto;
import com.travelagency.voyageconnect1.domain.dto.FlightDto;
import com.travelagency.voyageconnect1.domain.dto.HotelDto;
import com.travelagency.voyageconnect1.service.TravelSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/travel")
public class TravelSearchController {

    private final TravelSearchService travelSearchService;

    public TravelSearchController(TravelSearchService travelSearchService) {
        this.travelSearchService = travelSearchService;
    }

    @GetMapping("/flights")
    public Page<FlightDto> searchFlights(@RequestParam Long departureDestinationId,
                                                          @RequestParam Long arrivalDestinationId,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                                          Pageable pageable) {
        return travelSearchService.findFlights(departureDestinationId, arrivalDestinationId, departureDate, pageable);
    }

    @GetMapping("/hotels")
    public Page<HotelDto> searchHotels(@RequestParam Long destinationId,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                                                        Pageable pageable) {
        return travelSearchService.findHotels(destinationId, checkInDate, checkOutDate, pageable);
    }

    @GetMapping("/circuits")
    public Page<CircuitDto> searchCircuits(@RequestParam Long destinationId, Pageable pageable) {
        return travelSearchService.findCircuits(destinationId, pageable);
    }
}
