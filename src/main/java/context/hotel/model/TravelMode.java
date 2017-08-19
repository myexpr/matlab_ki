package context.hotel.model;

/**
 * Created by araman on 18/08/2017.
 */
public enum TravelMode {

  ROAD("DRIVING"), //within same country && d < 300 km
  RAIL("TRANSIT"), //within same country && d > 300 km
  AIR("UNDEFINED"); //different countries //within EU && d > 500

  private String apiTransitMode;

  TravelMode(String apiTransitMode) {
    this.apiTransitMode = apiTransitMode;
  }

  public String apiTransitMode() {
    return apiTransitMode;
  }

  @Override
  public String toString() {
    return "TravelMode{" +
        "apiTransitMode='" + apiTransitMode + '\'' +
        '}';
  }
}
