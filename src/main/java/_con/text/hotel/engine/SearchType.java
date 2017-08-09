package _con.text.hotel.engine;

import _con.text.hotel.constraint.Constraint;
import _con.text.hotel.constraint.Eq;
import _con.text.hotel.constraint.GteLte;
import _con.text.hotel.model.SearchRequest;

public enum SearchType {

  BUSINESS(new Eq(1), new Eq(1), new GteLte(1, 3));

  private final Constraint numberOfRooms;
  private final Constraint numberOfAdults;
  private final Constraint numberOfNights;

  SearchType(Constraint numberOfRooms, Constraint numberOfAdults,
      Constraint numberOfNights) {
    this.numberOfRooms = numberOfRooms;
    this.numberOfAdults = numberOfAdults;
    this.numberOfNights = numberOfNights;
  }

  public boolean evaluate(SearchRequest request) {
    return numberOfRooms.evaluate(request.numberOfRooms()) &&
        numberOfAdults.evaluate(request.numberOfAdults()) &&
        numberOfNights.evaluate(request.numberOfNights());
  }

}
