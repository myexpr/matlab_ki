package context.hotel.contextof.occupancy;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.of;

import context.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Optional;

public class OverWeekday implements OccupancyConstraint {

  private Integer weight;

  public OverWeekday() {
  }

  public OverWeekday(Integer weight) {
    this.weight = weight;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    DayOfWeek startDay = determineDayOfWeek(request.getFromDate());
    DayOfWeek endDay = determineDayOfWeek(request.getToDate());

    return startDay.getValue() >= MONDAY.getValue() &&
        endDay.getValue() <= FRIDAY.getValue();
  }

  @Override
  public Integer constraintWeight() {
    return Optional.ofNullable(weight).get();
  }

  protected DayOfWeek determineDayOfWeek(LocalDate date) {
    return of(date.get(ChronoField.DAY_OF_WEEK));
  }
}
