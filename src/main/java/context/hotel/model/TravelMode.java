package context.hotel.model;

/**
 * Created by araman on 18/08/2017.
 */
public enum TravelMode {

  ROAD("driving"), //within same country && d < 300 km
  RAIL("transit"), //within same country && d > 300 km
  AIR("AIR"); //different countries //within EU && d > 500

  private String apiTransitMode;

  TravelMode(String apiTransitMode) {
    this.apiTransitMode = apiTransitMode;
  }

  public String apiTransitMode() {
    return apiTransitMode;
  }

  @Override
  public String toString() {
    return name();
  }
}
