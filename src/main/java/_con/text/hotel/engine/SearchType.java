package _con.text.hotel.engine;

import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.adultCount;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.isWeekday;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.isWeekend;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.nights;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.roomCount;
import static _con.text.hotel.constraint.simple.ConstraintFactory.eq;
import static _con.text.hotel.constraint.simple.ConstraintFactory.gtelte;
import static java.util.Arrays.asList;

import _con.text.hotel.constraint.business.BusinessConstraints;
import _con.text.hotel.model.SearchRequest;
import java.util.List;

public enum SearchType {

  BUSINESS_TRIP(asList(adultCount(eq(1)),
      roomCount(eq(1)),
      nights(gtelte(1, 3)),
      isWeekday())),
  WEEKEND_GETAWAY(asList(
      adultCount(eq(2)),
      roomCount(eq(1)),
      isWeekend()
  ));

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
