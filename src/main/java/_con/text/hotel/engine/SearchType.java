package _con.text.hotel.engine;

import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.adultCount;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.childrenCount;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.isWeekday;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.isWeekend;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.nights;
import static _con.text.hotel.constraint.business.BusinessConstraintsFactory.roomCount;
import static _con.text.hotel.constraint.simple.ConstraintFactory.eq;
import static _con.text.hotel.constraint.simple.ConstraintFactory.gtelte;
import static java.lang.Math.*;
import static java.util.Arrays.asList;

import _con.text.hotel.constraint.business.BusinessConstraints;
import _con.text.hotel.model.SearchRequest;
import java.util.List;

public enum SearchType {

  BUSINESS_TRIP(adultCount(eq(1)),
      roomCount(eq(1)),
      nights(gtelte(1, 3)),
      isWeekday()),
  WEEKEND_GETAWAY_ADULTS(
      adultCount(gtelte(1, 4)),
      childrenCount(eq(0)),
      roomCount(gtelte(1, 2)),
      isWeekend()),
  WEEKEND_GETAWAY_NUCLEAR_FAMILY(
      adultCount(gtelte(1, 2)),
      childrenCount(gtelte(1, 3)),
      roomCount(gtelte(1, 2)),
      isWeekend()),
  WEEKEND_GETAWAY_FAMILIES(
      adultCount(gtelte(3, 4)),
      childrenCount(gtelte(3, 5)),
      roomCount(gtelte(2, 3)),
      isWeekend()),
  VACATION_NUCLEAR_FAMILY(
      adultCount(gtelte(1, 2)),
      childrenCount(gtelte(1, 3)),
      roomCount(gtelte(1, 2)),
      nights(gtelte(3, 10)));

  private final List<BusinessConstraints> constraints;

  SearchType(BusinessConstraints... constraints) {
    this.constraints = asList(constraints);
  }

  public boolean evaluate(SearchRequest request) {
    long countOfPassConstraints = getCountOfPassConstraints(request);
    return constraints.size() == countOfPassConstraints;
  }

  public PartialMatch probabilisticEvaluation(SearchRequest request) {
    long countOfPassConstraints = getCountOfPassConstraints(request);
    Integer probability = toIntExact(
        round((new Double(countOfPassConstraints) / new Double(constraints.size())) * 100));
    return new PartialMatch(this, probability);
  }

  private long getCountOfPassConstraints(SearchRequest request) {
    return constraints.stream().filter(c -> {
        return c.evaluate(request);
      }).count();
  }
}
