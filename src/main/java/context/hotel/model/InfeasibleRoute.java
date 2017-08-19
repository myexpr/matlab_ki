package context.hotel.model;

/**
 * Created by araman on 19/08/2017.
 */
public class InfeasibleRoute extends TimeDistance {

  public InfeasibleRoute(Integer distance, Integer time) {
    this();
  }

  public InfeasibleRoute() {
    super(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }

  @Override
  public String toString() {
    return "InfeasibleRoute{}";
  }
}
