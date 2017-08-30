package context.hotel.contextof.occupancy;

import static java.util.Optional.ofNullable;

import context.hotel.contextof.occupancy.math.Constraint;
import context.hotel.model.SearchRequest;

public class NumberOfAdults implements OccupancyConstraint {

  private Constraint constraint;
  private Integer weight;

  public NumberOfAdults(Constraint constraint) {
    this.constraint = constraint;
  }

  public NumberOfAdults(Constraint constraint, Integer weight) {
    this.constraint = constraint;
    this.weight = weight;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfAdults());
  }

  @Override
  public Integer constraintWeight() {
    return ofNullable(weight).get();
  }
}
