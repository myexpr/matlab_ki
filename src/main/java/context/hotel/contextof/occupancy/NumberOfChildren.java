package context.hotel.contextof.occupancy;

import static java.util.Optional.ofNullable;

import context.hotel.contextof.occupancy.math.Constraint;
import context.hotel.model.SearchRequest;

/**
 * Created by araman on 11/08/2017.
 */
public class NumberOfChildren implements OccupancyConstraint {

  private Constraint constraint;
  private Integer weight;

  public NumberOfChildren(Constraint c) {
    this.constraint = c;
  }

  public NumberOfChildren(Constraint constraint, Integer weight) {
    this.constraint = constraint;
    this.weight = weight;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfChildren());
  }

  @Override
  public Integer constraintWeight() {
    return ofNullable(weight).get();
  }
}
