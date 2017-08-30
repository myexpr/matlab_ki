package context.hotel.contextof.occupancy;

import context.hotel.contextof.occupancy.math.Constraint;

/**
 * Created by araman on 11/08/2017.
 */
public class OccupancyConstraintFactory {

  public static NumberOfAdults adultCount(Constraint c) {
    return new NumberOfAdults(c);
  }

  public static NumberOfChildren childrenCount(Constraint c) {
    return new NumberOfChildren(c);
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

  public static NumberOfAdults adultCount(Constraint c, Integer weight) {
    return new NumberOfAdults(c, weight);
  }

  public static NumberOfChildren childrenCount(Constraint c, Integer weight) {
    return new NumberOfChildren(c, weight);
  }

  public static NumberOfRooms roomCount(Constraint c, Integer weight) {
    return new NumberOfRooms(c, weight);
  }

  public static NumberOfNights nights(Constraint c, Integer weight) {
    return new NumberOfNights(c, weight);
  }

  public static OverWeekday isWeekday(Integer weight) {
    return new OverWeekday(weight);
  }

  public static OverWeekend isWeekend(Integer weight) {
    return new OverWeekend(weight);
  }
}
