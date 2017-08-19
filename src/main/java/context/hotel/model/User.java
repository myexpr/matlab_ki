package context.hotel.model;

/**
 * Created by araman on 18/08/2017.
 */
public class User {

  GeoCoordinate geoCoordinate;

  public User(GeoCoordinate geoCoordinate) {
    this.geoCoordinate = geoCoordinate;
  }

  public User() {
  }

  public GeoCoordinate getGeoCoordinate() {
    return geoCoordinate;
  }

  @Override
  public String toString() {
    return "User{" +
        "geoCoordinate=" + geoCoordinate +
        '}';
  }
}
