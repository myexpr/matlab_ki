package context.hotel.contextof.occupancy;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SUNDAY;

import context.hotel.model.SearchRequest;
import java.time.DayOfWeek;

public class OverWeekend extends OverWeekday {

  public OverWeekend() {
    super();
  }

  public OverWeekend(Integer weight) {
    super(weight);
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    DayOfWeek startDay = determineDayOfWeek(request.getFromDate());
    DayOfWeek endDay = determineDayOfWeek(request.getToDate());

    return startDay.getValue() >= FRIDAY.getValue() &&
        endDay.getValue() <= SUNDAY.getValue();
  }
}
