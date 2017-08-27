package context.hotel.model.response;

import context.hotel.model.Feasibility;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import java.util.Objects;

public class TravelModeMatch implements ContextMatch {

  TimeDistance timeDistance;
  Feasibility feasibility;
  TravelMode travelMode;

  public TravelModeMatch(TravelMode travelMode, Feasibility feasibility,
      TimeDistance timeDistance) {
    this.timeDistance = timeDistance;
    this.feasibility = feasibility;
    this.travelMode = travelMode;
  }

  public TimeDistance getTimeDistance() {
    return timeDistance;
  }

  @Override
  public String getContextCategory() {
    return "TravelContext";
  }

  @Override
  public String getContextType() {
    return this.travelMode.name();
  }

  public Feasibility getFeasibility() {
    return this.feasibility;
  }

  @Override
  public Object getData() {
    return getTimeDistance();
  }

  public TravelMode getTravelMode() {
    return travelMode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TravelModeMatch that = (TravelModeMatch) o;
    return Objects.equals(timeDistance, that.timeDistance) &&
        feasibility == that.feasibility &&
        travelMode == that.travelMode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeDistance, feasibility, travelMode);
  }

  @Override
  public String toString() {
    return "{" +
        "mode=" + getTravelMode() +
        ", time=" + timeDistance.getTime() +
        ", distance=" + timeDistance.getDistance() +
        ", feasibility=" + feasibility +
        '}';
  }
}
