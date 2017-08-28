package context.hotel.model;

/**
 * Created by araman on 18/08/2017.
 */
public class User {

  GeoCoordinate geoCoordinate;
  String accessToken;

  public User() {
  }

  public User(GeoCoordinate geoCoordinate) {
    this.geoCoordinate = geoCoordinate;
  }

  public User(String accessToken) {
    this.accessToken = accessToken;
  }

  public User(String accessToken, GeoCoordinate geoCoordinate) {
    this.accessToken = accessToken;
    this.geoCoordinate = geoCoordinate;
  }

  public GeoCoordinate getGeoCoordinate() {
    return geoCoordinate;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
    this.geoCoordinate = geoCoordinate;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override
  public String toString() {
    return "User{" +
        "geoCoordinate=" + geoCoordinate +
        ", accessToken='" + accessToken + '\'' +
        '}';
  }

  public boolean hasAccessToken() {
    return accessToken != null && accessToken.length() > 0;
  }
}
