package context.hotel.constraint.business;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.of;

import context.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class OverWeekday implements BusinessConstraints {

  @Override
  public boolean evaluate(SearchRequest request) {
    DayOfWeek startDay = determineDayOfWeek(request.getFromDate());
    DayOfWeek endDay = determineDayOfWeek(request.getToDate());

    return startDay.getValue() >= MONDAY.getValue() &&
        endDay.getValue() <= FRIDAY.getValue();
  }

  protected DayOfWeek determineDayOfWeek(LocalDate date) {
    return of(date.get(ChronoField.DAY_OF_WEEK));
  }
}
