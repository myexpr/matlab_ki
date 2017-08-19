package context.hotel.contextof.occupancy;

import context.hotel.contextof.occupancy.math.Constraint;
import context.hotel.model.SearchRequest;

public class NumberOfAdults implements OccupancyConstraint {

  private final Constraint constraint;

  public NumberOfAdults(Constraint constraint) {
    this.constraint = constraint;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfAdults());
  }
}
