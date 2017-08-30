package context.hotel.contextof.travelmode;

import static context.hotel.model.Feasibility.PREFERRED;
import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import context.hotel.model.Airport;
import context.hotel.model.Destination;
import context.hotel.model.GeoCoordinate;
import context.hotel.model.NullSafeLoggedUser;
import context.hotel.model.NullSafeUser;
import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import context.hotel.repository.AirportRepository;
import context.hotel.repository.DestinationRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by araman on 30/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirTest {

  @Autowired
  DestinationRepository destinationRepository;

  @Autowired
  AirportRepository airportRepository;

  @Autowired
  Air airTravel;

  @Test
  public void findAtleast5AirportsCloseToWGCWhenFullyQualifiedDestinationIsKnown() {
    Destination wgc = destinationRepository.findOne("50546");
    GeoCoordinate dGeoCoord = new GeoCoordinate(wgc.getLatitude(), wgc.getLongitude());
    SearchRequest searchRequest = new SearchRequest("50546", now(), now().plusDays(1),
        new Room(1, 0), new NullSafeUser());
    searchRequest.setResolvedDestination(wgc);
    searchRequest.setResolvedUser(new NullSafeLoggedUser());

    List<Double> largeAirportsNearby = airTravel.findActiveAirportsWithin100Km(dGeoCoord);
    assertTrue(largeAirportsNearby.size() == 5);
    assertTrue(largeAirportsNearby.get(0) < 15000d);

    assertEquals(PREFERRED, airTravel.determineFeasibility(searchRequest));
  }

  @Test
  public void findAllActiveAirports() {
    List<Airport> allActiveLargeAirports = airportRepository.findAllActiveLargeAirports();
    assertTrue(allActiveLargeAirports.size() > 500);
  }


}