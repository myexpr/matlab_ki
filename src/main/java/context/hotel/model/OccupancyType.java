package context.hotel.model;

import static context.hotel.contextof.occupancy.OccupancyConstraintFactory.adultCount;
import static context.hotel.contextof.occupancy.OccupancyConstraintFactory.childrenCount;
import static context.hotel.contextof.occupancy.OccupancyConstraintFactory.isWeekday;
import static context.hotel.contextof.occupancy.OccupancyConstraintFactory.isWeekend;
import static context.hotel.contextof.occupancy.OccupancyConstraintFactory.nights;
import static context.hotel.contextof.occupancy.OccupancyConstraintFactory.roomCount;
import static context.hotel.contextof.occupancy.math.ConstraintFactory.eq;
import static context.hotel.contextof.occupancy.math.ConstraintFactory.gtelte;
import static java.lang.Math.round;
import static java.lang.Math.toIntExact;
import static java.util.Arrays.asList;

import context.hotel.contextof.occupancy.OccupancyConstraint;
import context.hotel.model.response.OccupancyTypeMatch;
import java.util.List;

public enum OccupancyType {

  BUSINESS_TRIP(adultCount(eq(1), 20),
      roomCount(eq(1), 20),
      nights(gtelte(1, 3), 10),
      isWeekday(50)),
  WEEKEND_GETAWAY_ADULTS(
      adultCount(gtelte(1, 4), 30),
      childrenCount(eq(0), 10),
      roomCount(gtelte(1, 2), 10),
      isWeekend(50)),
  WEEKEND_GETAWAY_NUCLEAR_FAMILY(
      adultCount(gtelte(1, 2), 30),
      childrenCount(gtelte(1, 3), 30),
      roomCount(gtelte(1, 2), 10),
      isWeekend(30)),
  WEEKEND_GETAWAY_FAMILIES(
      adultCount(gtelte(3, 4), 30),
      childrenCount(gtelte(3, 5), 30),
      roomCount(gtelte(2, 3), 10),
      isWeekend(30)),
  VACATION_NUCLEAR_FAMILY(
      adultCount(gtelte(1, 2), 30),
      childrenCount(gtelte(1, 3), 30),
      roomCount(gtelte(1, 2), 10),
      nights(gtelte(3, 10), 30));

  private final List<OccupancyConstraint> constraints;

  OccupancyType(OccupancyConstraint... constraints) {
    this.constraints = asList(constraints);
  }

  public boolean evaluate(SearchRequest request) {
    long countOfPassConstraints = getCountOfPassConstraints(request);
    return constraints.size() == countOfPassConstraints;
  }

  public OccupancyTypeMatch probabilisticEvaluation(SearchRequest request) {
    long countOfPassConstraints = getCountOfPassConstraints(request);
    Integer probability = toIntExact(
        round((new Double(countOfPassConstraints) / new Double(constraints.size())) * 100));
    return new OccupancyTypeMatch(this, probability);
  }

  public OccupancyTypeMatch weightedEvaluation(SearchRequest stayFor9Days) {
    int sumOfWeights = constraints
        .stream()
        .filter(c -> c.evaluate(stayFor9Days))
        .mapToInt(OccupancyConstraint::constraintWeight)
        .sum();

    return new OccupancyTypeMatch(this, sumOfWeights);
  }

  private long getCountOfPassConstraints(SearchRequest request) {
    return constraints
        .stream()
        .filter(c -> c.evaluate(request))
        .count();
  }

}
