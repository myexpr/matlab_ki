package _con.text.hotel.constraint.arithmetic;

/**
 * Created by araman on 09/08/2017.
 */
public class GteLte implements Constraint {

  private final Integer lowerBound;
  private final Integer uppperBound;

  public GteLte(Integer lowerBound, Integer upperBound) {
    this.lowerBound = lowerBound;
    this.uppperBound = upperBound;
  }

  @Override
  public boolean evaluate(Integer value) {
    return value >= this.lowerBound && value
        <= this.uppperBound;
  }
}
