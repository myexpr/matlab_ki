package context.hotel.model.response;


import static context.hotel.model.Feasibility.*;
import static context.hotel.model.Feasibility.INFEASIBLE;

import context.hotel.model.Feasibility;
import context.hotel.model.OccupancyType;
import java.util.Objects;

public class OccupancyTypeMatch implements ContextMatch, Comparable<OccupancyTypeMatch> {

  Integer matchPercentage;
  OccupancyType occupancyType;

  public OccupancyTypeMatch(OccupancyType occupancyType, Integer matchPercentage) {
    this.matchPercentage = matchPercentage;
    this.occupancyType = occupancyType;
  }

  public Integer getMatchPercentage() {
    return matchPercentage;
  }

  public OccupancyType getOccupancyType() {
    return occupancyType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OccupancyTypeMatch that = (OccupancyTypeMatch) o;
    return Objects.equals(matchPercentage, that.matchPercentage) &&
        occupancyType == that.occupancyType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(matchPercentage, occupancyType);
  }

  @Override
  public String toString() {
    return "OccupancyTypeMatch{" +
        "occupancyType=" + occupancyType +
        ", matchPercentage=" + matchPercentage +
        '}';
  }

  @Override
  public String getContextCategory() {
    return "OccupancyContext";
  }

  @Override
  public String getContextType() {
    return this.occupancyType.name();
  }

  @Override
  public Feasibility getFeasibility() {
    if (matchPercentage < 60) {
      return INFEASIBLE;
    }
    if (matchPercentage >= 60 && matchPercentage < 80) {
      return REASONABLE_STRETCH;
    }
    if (matchPercentage >= 80) {
      return PROBABLE;
    }
    return INFEASIBLE;
  }

  @Override
  public Object getData() {
    return this.matchPercentage;
  }

  @Override
  public int compareTo(OccupancyTypeMatch other) {
    //intentional reverse sort
    return other.matchPercentage.compareTo(this.matchPercentage);
  }
}
