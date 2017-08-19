package context.hotel.model;

import java.util.Objects;

public class TravelModeMatch {

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

  public Feasibility getFeasibility() {
    return feasibility;
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
