package context.hotel.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Destination {

  public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

  @Id
  Integer destinationId;
  String city;
  String region;
  String countryCode;
  Double latitude;
  Double longitude;

  public Destination() {
  }

  public Destination(Integer destinationId, String city, String region, String countryCode,
      Double latitude, Double longitude) {
    this.destinationId = destinationId;
    this.city = city;
    this.region = region;
    this.countryCode = countryCode;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Integer getDestinationId() {
    return destinationId;
  }

  public String getCity() {
    return city;
  }

  public String getRegion() {
    return region;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Destination that = (Destination) o;
    return Objects.equals(destinationId, that.destinationId) &&
        Objects.equals(city, that.city) &&
        Objects.equals(region, that.region) &&
        Objects.equals(countryCode, that.countryCode) &&
        Objects.equals(latitude, that.latitude) &&
        Objects.equals(longitude, that.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(destinationId);
  }

  @Override
  public String toString() {
    return "Destination{" +
        "destinationId=" + destinationId +
        ", city='" + city + '\'' +
        ", region='" + region + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        '}';
  }

  public Integer distanceFrom(double aLatitude, double aLongitude) {

    double latDistance = Math.toRadians(latitude - aLatitude);
    double lngDistance = Math.toRadians(longitude - aLongitude);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(aLatitude))
        * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
  }
}
