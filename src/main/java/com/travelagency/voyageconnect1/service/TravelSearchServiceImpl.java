package com.travelagency.voyageconnect1.service;

import com.travelagency.voyageconnect1.domain.dto.CircuitDto;
import com.travelagency.voyageconnect1.domain.dto.FlightDto;
import com.travelagency.voyageconnect1.domain.dto.HotelDto;
import com.travelagency.voyageconnect1.domain.entity.Circuit;
import com.travelagency.voyageconnect1.domain.entity.Flight;
import com.travelagency.voyageconnect1.domain.entity.Hotel;
import com.travelagency.voyageconnect1.domain.mapper.CircuitMapper;
import com.travelagency.voyageconnect1.domain.mapper.FlightMapper;
import com.travelagency.voyageconnect1.domain.mapper.HotelMapper;
import com.travelagency.voyageconnect1.repository.CircuitRepository;
import com.travelagency.voyageconnect1.repository.FlightRepository;
import com.travelagency.voyageconnect1.repository.HotelRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
public class TravelSearchServiceImpl implements TravelSearchService {

    private final FlightRepository flightRepository;
    private final HotelRepository hotelRepository;
    private final CircuitRepository circuitRepository;
    private final FlightMapper flightMapper;
    private final HotelMapper hotelMapper;
    private final CircuitMapper circuitMapper;

    public TravelSearchServiceImpl(FlightRepository flightRepository, HotelRepository hotelRepository,
                                   CircuitRepository circuitRepository, FlightMapper flightMapper,
                                   HotelMapper hotelMapper, CircuitMapper circuitMapper) {
        this.flightRepository = flightRepository;
        this.hotelRepository = hotelRepository;
        this.circuitRepository = circuitRepository;
        this.flightMapper = flightMapper;
        this.hotelMapper = hotelMapper;
        this.circuitMapper = circuitMapper;
    }

    @Override
    @Cacheable("flights")
    @Retry(name = "flightSearch")
    public Page<FlightDto> findFlights(Long departureDestinationId, Long arrivalDestinationId, LocalDate departureDate, Pageable pageable) {
        Specification<Flight> spec = Specification.where(FlightSpecifications.withDepartureDestination(departureDestinationId))
                .and(FlightSpecifications.withArrivalDestination(arrivalDestinationId))
                .and(FlightSpecifications.withDepartureDate(departureDate));
        return flightRepository.findAll(spec, pageable).map(flightMapper::toDto);
    }

    @Override
    @Cacheable("hotels")
    @Retry(name = "hotelSearch")
    public Page<HotelDto> findHotels(Long destinationId, LocalDate checkInDate, LocalDate checkOutDate, Pageable pageable) {
        Specification<Hotel> spec = Specification.where(HotelSpecifications.withDestination(destinationId));
        // We could add more complex logic for availability based on check-in/out dates
        return hotelRepository.findAll(spec, pageable).map(hotelMapper::toDto);
    }

    @Override
    @Cacheable("circuits")
    @Retry(name = "circuitSearch")
    public Page<CircuitDto> findCircuits(Long destinationId, Pageable pageable) {
        Specification<Circuit> spec = Specification.where(CircuitSpecifications.withDestination(destinationId));
        return circuitRepository.findAll(spec, pageable).map(circuitMapper::toDto);
    }

    // Specification classes for dynamic queries
    private static class FlightSpecifications {
        static Specification<Flight> withDepartureDestination(Long destinationId) {
            return (root, query, cb) -> cb.equal(root.get("departureDestination").get("id"), destinationId);
        }
        static Specification<Flight> withArrivalDestination(Long destinationId) {
            return (root, query, cb) -> cb.equal(root.get("arrivalDestination").get("id"), destinationId);
        }
        static Specification<Flight> withDepartureDate(LocalDate date) {
            return (root, query, cb) -> cb.between(root.get("departureTime"), date.atStartOfDay(), date.atTime(LocalTime.MAX));
        }
    }

    private static class HotelSpecifications {
        static Specification<Hotel> withDestination(Long destinationId) {
            return (root, query, cb) -> cb.equal(root.get("destination").get("id"), destinationId);
        }
    }

    private static class CircuitSpecifications {
        static Specification<Circuit> withDestination(Long destinationId) {
            return (root, query, cb) -> cb.equal(root.get("destination").get("id"), destinationId);
        }
    }
}
