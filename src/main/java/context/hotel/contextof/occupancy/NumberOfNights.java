package context.hotel.contextof.occupancy;

import context.hotel.contextof.occupancy.math.Constraint;
import context.hotel.model.SearchRequest;
import java.util.Optional;

public class NumberOfNights implements OccupancyConstraint {

  private final Constraint constraint;
  private Integer weight;

  public NumberOfNights(Constraint constraint) {
    this.constraint = constraint;
  }

  public NumberOfNights(Constraint constraint, Integer weight) {
    this.constraint = constraint;
    this.weight = weight;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfNights());
  }

  @Override
  public Integer constraintWeight() {
    return Optional.ofNullable(weight).get();
  }
}
