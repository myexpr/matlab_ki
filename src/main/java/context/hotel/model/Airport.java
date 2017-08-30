package context.hotel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by araman on 30/08/2017.
 */
@Entity
public class Airport {

  private static final Logger LOGGER = LoggerFactory.getLogger(Airport.class);

  @Id
  String id;

  String type;
  String name;
  String continent;
  String isoCountry;
  String isoRegion;
  String municipality;
  String scheduledService;
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "latitude_deg")),
      @AttributeOverride(name = "longitude", column = @Column(name = "longitude_deg"))
  })
  GeoCoordinate airportGeoCoordinate;

  public Airport() {
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getContinent() {
    return continent;
  }

  public String getIsoCountry() {
    return isoCountry;
  }

  public String getIsoRegion() {
    return isoRegion;
  }

  public String getMunicipality() {
    return municipality;
  }

  public String getScheduledService() {
    return scheduledService;
  }

  public GeoCoordinate getAirportGeoCoordinate() {
    return airportGeoCoordinate;
  }

  @Override
  public String toString() {
    return "Airport{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", isoCountry='" + isoCountry + '\'' +
        ", airportGeoCoordinate=" + airportGeoCoordinate +
        '}';
  }

  public Double distanceFrom(GeoCoordinate destinationGeoCoordinate) {
    Double aDouble = airportGeoCoordinate.distanceFrom(destinationGeoCoordinate);
//    LOGGER.debug("distance from {} to {} is {}", name, destinationGeoCoordinate, aDouble);
    return aDouble;
  }
}
