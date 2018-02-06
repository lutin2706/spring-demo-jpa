package be.superteam.springdemojpa.repository;

import be.superteam.springdemojpa.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TripRepo extends JpaRepository<Trip, Long> {

    List<Trip> findAllByDestination(String destination);

    List<Trip> findAllByDepartureDateBeforeOrderByDaysDesc(LocalDate departureDate);

    @Query("SELECT t from Trip t WHERE t.days = 2")
    List<Trip> findTwoDaysTrips();

    @Query("SELECT t from Trip t WHERE t.days = ?1")
    List<Trip> findTripsByDays(int days);
}
