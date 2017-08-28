package context.hotel.model;

/**
 * Created by araman on 28/08/2017.
 */
public class NullSafeUser extends User {

  public NullSafeUser() {
    this.accessToken = "";
    this.geoCoordinate = new NullSafeGeoCoordinate();
  }

  @Override
  public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
    throw new UnsupportedOperationException("null safe class. doesnt support set");
  }

  @Override
  public void setAccessToken(String accessToken) {
    throw new UnsupportedOperationException("null safe class. doesnt support set");
  }

  @Override
  public String toString() {
    return "NullSafeUser{" +
        "geoCoordinate=" + geoCoordinate +
        ", accessToken='" + accessToken + '\'' +
        '}';
  }
}
