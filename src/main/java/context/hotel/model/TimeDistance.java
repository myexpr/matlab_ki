package context.hotel.model;

/**
 * Created by araman on 19/08/2017.
 */
public class TimeDistance {

  Integer distance;
  Integer time;

  public TimeDistance(Integer distance, Integer time) {
    this.distance = distance;
    this.time = time;
  }

  public TimeDistance(Double doubleDistance, int time) {
    this(doubleDistance.intValue(), time);
  }

  public Integer getDistance() {
    return distance;
  }

  public Integer getTime() {
    return time;
  }

  @Override
  public String toString() {
    return "TimeDistance{" +
        "distance=" + distance +
        ", time=" + time +
        '}';
  }
}
