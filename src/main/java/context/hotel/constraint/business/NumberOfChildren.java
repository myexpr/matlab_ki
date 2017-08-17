package context.hotel.constraint.business;

import context.hotel.constraint.simple.Constraint;
import context.hotel.model.SearchRequest;

/**
 * Created by araman on 11/08/2017.
 */
public class NumberOfChildren implements BusinessConstraints {

  private final Constraint constraint;

  public NumberOfChildren(Constraint c) {
    this.constraint = c;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfChildren());
  }
}
