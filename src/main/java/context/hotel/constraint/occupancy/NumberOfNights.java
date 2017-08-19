package context.hotel.constraint.occupancy;

import context.hotel.constraint.simple.Constraint;
import context.hotel.model.SearchRequest;

public class NumberOfNights implements OccupancyConstraint {

  private final Constraint constraint;

  public NumberOfNights(Constraint constraint) {
    this.constraint = constraint;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfNights());
  }
}
