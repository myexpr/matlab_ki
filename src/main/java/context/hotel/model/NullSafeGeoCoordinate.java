package context.hotel.model;

import static java.lang.Boolean.*;

/**
 * Created by araman on 28/08/2017.
 */
public class NullSafeGeoCoordinate extends GeoCoordinate {

  public NullSafeGeoCoordinate() {
    this.latitude = 0d;
    this.longitude = 0d;
  }

  @Override
  public void setLatitude(Double latitude) {
    throw new UnsupportedOperationException("null safe class. doesnt support set");
  }

  @Override
  public void setLongitude(Double longitude) {
    throw new UnsupportedOperationException("null safe class. doesnt support set");
  }

  @Override
  public boolean isValid() {
    return FALSE;
  }

  @Override
  public String toString() {
    return "NullSafeGeoCoordinate{" +
        "latitude=" + latitude +
        ", longitude=" + longitude +
        '}';
  }
}
