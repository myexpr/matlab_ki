package _con.text.hotel.constraint.business;

import _con.text.hotel.constraint.simple.Constraint;

/**
 * Created by araman on 11/08/2017.
 */
public class BusinessConstraintsFactory {

  public static NumberOfAdults adultCount(Constraint c) {
    return new NumberOfAdults(c);
  }

  public static NumberOfRooms roomCount(Constraint c) {
    return new NumberOfRooms(c);
  }

  public static NumberOfNights nights(Constraint c) {
    return new NumberOfNights(c);
  }

  public static OverWeekday isWeekday() {
    return new OverWeekday();
  }

  public static OverWeekend isWeekend() {
    return new OverWeekend();
  }
}
