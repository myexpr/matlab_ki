package context.hotel.contextof.travelmode;

import static context.hotel.model.TravelMode.AIR;

import context.hotel.model.Airport;
import context.hotel.model.Destination;
import context.hotel.model.Feasibility;
import context.hotel.model.GeoCoordinate;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
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
public class ModifiedAir implements Travel {

  private static final Logger LOGGER = LoggerFactory.getLogger(ModifiedAir.class);

  @Autowired
  AirportRepository airportRepository;
  List<Airport> allActiveLargeAirports;
  private double KmToM = 1000;

  @PostConstruct
  public void init() {
    allActiveLargeAirports = airportRepository.findAllActiveLargeAirports();
  }

  @Override
  public TimeDistance determineTimeDistance(SearchRequest request) {
    LOGGER.debug("determineTimeDistance invocation ignored");
    return null;
  }

  @Override
  public Feasibility determineFeasibility(TimeDistance timeDistance, SearchRequest request) {
    Feasibility result = Feasibility.INFEASIBLE;
    Destination d = request.getResolvedDestination();
    GeoCoordinate destGeoCoordinate = new GeoCoordinate(d.getLatitude(), d.getLongitude());
    List<Double> distances = findActiveAirportsWithin100Km(destGeoCoordinate);

    LOGGER.debug("sorted Distances to destination {} is {} ", d.name(), distances);

    if (airportsWithin(distances, 0d, 20d * KmToM)) {
      result = Feasibility.PREFERRED;
    } else if (airportsWithin(distances, 20d * KmToM, 50d * KmToM)) {
      result = Feasibility.PROBABLE;
    } else if (airportsWithin(distances, 50d * KmToM, 70d * KmToM)) {
      result = Feasibility.REASONABLE_STRETCH;
    } else if (airportsWithin(distances, 70d * KmToM, 100d * KmToM)) {
      result = Feasibility.DIFFICULT;
    }
    return result;
  }

  private boolean airportsWithin(List<Double> distances, Double lowerBound,
      Double upperBound) {
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

  @Override
  public TravelMode forTravelMode() {
    return AIR;
  }

}

