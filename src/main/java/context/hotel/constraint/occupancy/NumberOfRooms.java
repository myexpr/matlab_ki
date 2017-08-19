package context.hotel.constraint.occupancy;

import context.hotel.constraint.simple.Constraint;
import context.hotel.model.SearchRequest;

/**
 * Created by araman on 09/08/2017.
 */
public class NumberOfRooms implements OccupancyConstraint {

  private final Constraint constraint;

  public NumberOfRooms(Constraint constraint) {
    this.constraint = constraint;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfRooms());
  }
}
