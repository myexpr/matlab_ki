package context.hotel.model;

/**
 * Created by araman on 18/08/2017.
 */
public class GeoCoordinate {

  Double latitude;
  Double longitude;

  public GeoCoordinate() {
  }

  public GeoCoordinate(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return "GeoCoordinate{" +
        "latitude=" + latitude +
        ", longitude=" + longitude +
        '}';
  }

  public boolean isValid() {
    return latitude != null && longitude != null;
  }
}
