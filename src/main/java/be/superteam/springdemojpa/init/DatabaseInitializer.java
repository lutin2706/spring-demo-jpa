package be.superteam.springdemojpa.init;

import be.superteam.springdemojpa.model.Trip;
import be.superteam.springdemojpa.repository.TripRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseInitializer implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    private TripRepo tripRepo;

    public DatabaseInitializer(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        // Aller chercher les objets avec une pagination
        // tripRepo.findAll(new PageRequest(3, 20));

        Trip tripToLondon = new Trip("London", 2, LocalDate.now().plusDays(3), "Go and see the Queen and prince Charles.");
        Trip tripToLisbon = new Trip("Lisbon", 2, LocalDate.now().plusDays(5), "Marvelous Lisbon !");
        Trip tripToMadrid = new Trip("Madrid", 10, LocalDate.now().plusDays(40), "Eat tapas and drink sangria");

        // CREATE
        tripRepo.save(tripToLondon);
        tripRepo.save(tripToLisbon);
        tripRepo.save(tripToMadrid);

        // READ
        List<Trip> trips = tripRepo.findAll();
        for (Trip t : trips) {
            logger.warn(t.toString());
        }

        // FIND
        Trip example = new Trip();
        example.setDestination("London");
        Trip found = tripRepo.findOne((Example.of(example, ExampleMatcher.matchingAny())));
        logger.warn(found.toString());

        found.setDestination("Dundee");
        tripRepo.save(found);

        Trip foundUpdated = tripRepo.findOne(1L);
        logger.warn(foundUpdated.toString());

        // CUSTOM REQUEST WITH METHOD NAME
        List<Trip> dundees = tripRepo.findAllByDestination("Dundee");
        logger.warn("All destinations Dundee: " + dundees.toString());

        List<Trip> thisMonth = tripRepo.findAllByDepartureDateBeforeOrderByDaysDesc(LocalDate.now().plusDays(10));
        logger.warn("All destinations starting in next 10 days: " + thisMonth.toString());

        // CUSTOM REQUEST USING JPQL
        List<Trip> twoDays = tripRepo.findTwoDaysTrips();
        logger.warn("All trips of 2 days: " + twoDays.toString());

        List<Trip> twoDaysBis = tripRepo.findTripsByDays(10);
        logger.warn("All trips of 10 days: " + twoDaysBis);

        // DELETE
        tripRepo.delete(1L);
    }
}
