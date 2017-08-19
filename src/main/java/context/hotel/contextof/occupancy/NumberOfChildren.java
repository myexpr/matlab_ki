package context.hotel.contextof.occupancy;

import context.hotel.contextof.occupancy.math.Constraint;
import context.hotel.model.SearchRequest;

/**
 * Created by araman on 11/08/2017.
 */
public class NumberOfChildren implements OccupancyConstraint {

  private final Constraint constraint;

  public NumberOfChildren(Constraint c) {
    this.constraint = c;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfChildren());
  }
}
