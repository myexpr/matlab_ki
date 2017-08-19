package context.hotel.contextof.occupancy.math;

/**
 * Created by araman on 11/08/2017.
 */
public class ConstraintFactory {

  public static Eq eq(Integer i) {
    return new Eq(i);
  }

  public static GteLte gtelte(Integer lower, Integer upper) {
    return new GteLte(lower, upper);
  }

}
