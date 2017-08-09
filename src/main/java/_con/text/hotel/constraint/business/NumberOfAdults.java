package _con.text.hotel.constraint.business;

import _con.text.hotel.constraint.arithmetic.Constraint;
import _con.text.hotel.model.SearchRequest;

public class NumberOfAdults implements BusinessConstraints {

  private final Constraint constraint;

  public NumberOfAdults(Constraint constraint) {
    this.constraint = constraint;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    return constraint.evaluate(request.numberOfAdults());
  }
}
