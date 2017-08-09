package _con.text.hotel.engine;

import static java.util.Arrays.*;

import _con.text.hotel.constraint.business.BusinessConstraints;
import _con.text.hotel.constraint.business.OverWeekday;
import _con.text.hotel.constraint.simple.Eq;
import _con.text.hotel.constraint.simple.GteLte;
import _con.text.hotel.constraint.business.NumberOfAdults;
import _con.text.hotel.constraint.business.NumberOfNights;
import _con.text.hotel.constraint.business.NumberOfRooms;
import _con.text.hotel.model.SearchRequest;
import java.util.List;

public enum SearchType {

  BUSINESS(asList(
      new NumberOfAdults(new Eq(1)),
      new NumberOfRooms(new Eq(1)),
      new NumberOfNights(new GteLte(1, 3)),
      new OverWeekday()));

  private final List<BusinessConstraints> constraints;
    SearchType(List<BusinessConstraints> constraints) {
      this.constraints = constraints;
    }

  public boolean evaluate(SearchRequest request) {
    long countOfPassConstraints = constraints.stream().filter(c -> {
      return c.evaluate(request);
    }).count();

    return constraints.size() == countOfPassConstraints;
  }

}
