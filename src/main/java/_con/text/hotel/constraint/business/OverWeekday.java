package _con.text.hotel.constraint.business;

import static java.time.DayOfWeek.*;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.getInstance;

import _con.text.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;

public class OverWeekday implements BusinessConstraints {

  @Override
  public boolean evaluate(SearchRequest request) {
    DayOfWeek startDay = determineDayOfWeek(request.getFromDate());
    DayOfWeek endDay = determineDayOfWeek(request.getToDate());

    return startDay.getValue() >= MONDAY.getValue() &&
        endDay.getValue() <= FRIDAY.getValue();
  }

  private DayOfWeek determineDayOfWeek(LocalDate date) {
    return of(date.get(ChronoField.DAY_OF_WEEK));
  }
}
