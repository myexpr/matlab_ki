package context.hotel.model;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

import javax.persistence.Embeddable;

/**
 * Created by araman on 18/08/2017.
 */
@Embeddable
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

  public Double distanceFrom(GeoCoordinate other) {
    double earthRadius = 6371000; //meters
    double dLat = toRadians(other.getLatitude() - latitude);
    double dLng = toRadians(other.getLongitude() - longitude);
    double a = sin(dLat / 2) * sin(dLat / 2) +
        cos(toRadians(latitude)) * cos(toRadians(other.getLatitude())) *
            sin(dLng / 2) * sin(dLng / 2);
    double c = 2 * atan2(sqrt(a), sqrt(1 - a));
    double distanceInMeters = (earthRadius * c);

    return distanceInMeters;
  }
}
