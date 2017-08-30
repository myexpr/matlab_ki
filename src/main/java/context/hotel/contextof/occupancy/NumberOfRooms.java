package context.hotel.contextof.occupancy;

import context.hotel.contextof.occupancy.math.Constraint;
import context.hotel.model.SearchRequest;
import java.util.Optional;

/**
 * Created by araman on 09/08/2017.
 */
public class NumberOfRooms implements OccupancyConstraint {

  private final Constraint constraint;
  private Integer weight;

  public NumberOfRooms(Constraint constraint) {
    this.constraint = constraint;
  }

  public NumberOfRooms(Constraint constraint, Integer weight) {
    this.constraint = constraint;
    this.weight = weight;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfRooms());
  }

  @Override
  public Integer constraintWeight() {
    return Optional.ofNullable(weight).get();
  }
}
