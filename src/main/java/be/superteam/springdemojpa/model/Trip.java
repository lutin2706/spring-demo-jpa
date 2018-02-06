package be.superteam.springdemojpa.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destination;


    private int days;

    private LocalDate departureDate;

    @Column(length = Short.MAX_VALUE)
    private String description;

    public Trip() {
    }

    public Trip(String destination, int days, LocalDate departureDate, String description) {
        this.destination = destination;
        this.days = days;
        this.departureDate = departureDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", days=" + days +
                ", departureDate=" + departureDate +
                ", description='" + description + '\'' +
                '}';
    }
}
