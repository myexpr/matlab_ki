package context.hotel.contextof.occupancy;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.util.Arrays.asList;

import java.time.DayOfWeek;
import java.util.List;

public class OverWeekend extends OverWeekday {

  private List<DayOfWeek> WEEK_DAYS = asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

  public OverWeekend() {
    super();
  }

  public OverWeekend(Integer weight) {
    super(weight);
  }

  @Override
  protected List<DayOfWeek> forbiddenDays() {
    return this.WEEK_DAYS;
  }

}
