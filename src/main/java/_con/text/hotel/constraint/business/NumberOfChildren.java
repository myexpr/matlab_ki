package _con.text.hotel.constraint.business;

import _con.text.hotel.constraint.simple.Constraint;
import _con.text.hotel.model.SearchRequest;

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
