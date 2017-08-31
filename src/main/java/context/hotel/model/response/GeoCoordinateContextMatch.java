package context.hotel.model.response;

import static context.hotel.model.Feasibility.PREFERRED;

import context.hotel.model.Feasibility;
import context.hotel.model.GeoCoordinate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by araman on 31/08/2017.
 */
public class GeoCoordinateContextMatch implements ContextMatch {

  private final GeoCoordinate geoCoordinate;
  private final String contextType;
  private final String cityName;

  public GeoCoordinateContextMatch(String contextType, String cityName,
      GeoCoordinate geoCoordinate) {
    this.geoCoordinate = geoCoordinate;
    this.cityName = cityName;
    this.contextType = contextType;
  }

  @Override
  public String getContextCategory() {
    return "GeoCoordinateContext";
  }

  @Override
  public Feasibility getFeasibility() {
    return PREFERRED;
  }

  @Override
  public String getContextType() {
    return this.contextType;
  }

  @Override
  public Object getData() {
    Map<String, Object> dataSet = new HashMap<>();
    dataSet.put("cityname", this.cityName);
    dataSet.put("geocoordinate", this.geoCoordinate);
    return dataSet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GeoCoordinateContextMatch that = (GeoCoordinateContextMatch) o;

    if (!geoCoordinate.equals(that.geoCoordinate)) {
      return false;
    }
    return getContextType().equals(that.getContextType());
  }

  @Override
  public int hashCode() {
    int result = geoCoordinate.hashCode();
    result = 31 * result + getContextType().hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "GeoCoordinateContextMatch{" +
        "geoCoordinate=" + geoCoordinate +
        ", contextType='" + contextType + '\'' +
        '}';
  }
}
