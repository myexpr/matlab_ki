package _con.text.hotel.constraint;

/**
 * Created by araman on 09/08/2017.
 */
public class Eq implements Constraint {

  private final Integer threshold;

  public Eq(Integer threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean evaluate(Integer value) {
    return this.threshold == value;
  }
}
