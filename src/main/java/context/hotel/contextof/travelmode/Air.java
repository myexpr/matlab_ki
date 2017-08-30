package context.hotel.contextof.travelmode;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.Feasibility.PROBABLE;
import static context.hotel.model.Feasibility.REASONABLE_STRETCH;
import static context.hotel.model.TravelMode.AIR;

import context.hotel.model.Airport;
import context.hotel.model.Destination;
import context.hotel.model.Feasibility;
import context.hotel.model.GeoCoordinate;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import context.hotel.model.response.TravelModeMatch;
import context.hotel.repository.AirportRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 30/08/2017.
 */
@Component
public class Air implements Travel {

  private static final Logger LOGGER = LoggerFactory.getLogger(Air.class);

  @Autowired
  AirportRepository airportRepository;
  List<Airport> allActiveLargeAirports;

  private double KmToM = 1000;
  private TimeDistance distanceUpto20Km = new TimeDistance(20000, 0);
  private TimeDistance distanceBetween20To50Km = new TimeDistance(50000, 0);
  private TimeDistance distanceBetween50To70Km = new TimeDistance(70000, 0);
  private TimeDistance distanceBetween70To100Km = new TimeDistance(100000, 0);
  private TimeDistance distanceMoreThan100Km = new TimeDistance(100000, 0);

  @PostConstruct
  public void init() {
    allActiveLargeAirports = airportRepository.findAllActiveLargeAirports();
  }

  @Override
  public TravelModeMatch determineFeasibility(SearchRequest request) {
    Feasibility feasibility = INFEASIBLE;
    TimeDistance timeDistance = distanceMoreThan100Km;
    Destination d = request.getResolvedDestination();
    GeoCoordinate destGeoCoordinate = new GeoCoordinate(d.getLatitude(), d.getLongitude());

    List<Double> distances = findActiveAirportsWithin100Km(destGeoCoordinate);
    LOGGER.debug("sorted Distances to destination {} is {} ", d.name(), distances);

    if (airportsWithin(distances, 0d, 20d * KmToM)) {
      feasibility = PREFERRED;
      timeDistance = distanceUpto20Km;
    } else if (airportsWithin(distances, 20d * KmToM, 50d * KmToM)) {
      feasibility = PROBABLE;
      timeDistance = distanceBetween20To50Km;
    } else if (airportsWithin(distances, 50d * KmToM, 70d * KmToM)) {
      feasibility = REASONABLE_STRETCH;
      timeDistance = distanceBetween50To70Km;
    } else if (airportsWithin(distances, 70d * KmToM, 100d * KmToM)) {
      feasibility = DIFFICULT;
      timeDistance = distanceBetween70To100Km;
    }
    return new TravelModeMatch(forTravelMode(), feasibility, timeDistance);
  }

  private boolean airportsWithin(List<Double> distances, Double lowerBound, Double upperBound) {
    long count = distances
        .stream()
        .filter(d -> d >= lowerBound && d < upperBound)
        .count();
    return count > 0l;
  }

  List<Double> findActiveAirportsWithin100Km(GeoCoordinate geoCoordinate) {
    List<Double> nearestAirports = allActiveLargeAirports
        .stream()
        .map(airport -> airport.distanceFrom(geoCoordinate))
        .filter(distance -> distance <= 100d * 1000)
        .sorted()
        .collect(Collectors.toList());
    return nearestAirports;
  }

  public TravelMode forTravelMode() {
    return AIR;
  }

}

