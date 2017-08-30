package context.hotel.contextof.occupancy;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.of;
import static java.util.Arrays.asList;

import context.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;

public class OverWeekday implements OccupancyConstraint {

  private Integer weight;
  private List<DayOfWeek> WEEKEND_DAYS = asList(SATURDAY, SUNDAY);

  public OverWeekday() {
  }

  public OverWeekday(Integer weight) {
    this.weight = weight;
  }

  @Override
  public boolean evaluate(SearchRequest request) {
    LocalDate fromDate = request.getFromDate();
    LocalDate toDate = request.getToDate();
    LocalDate loopDate = fromDate;
    boolean result = true;
    while (loopDate.compareTo(toDate) <= 0 && result) {
      DayOfWeek dayOfWeek = determineDayOfWeek(loopDate);
      result = !forbiddenDays().contains(dayOfWeek);
      loopDate = loopDate.plusDays(1);
    }
    return result;
  }

  protected List<DayOfWeek> forbiddenDays() {
    return this.WEEKEND_DAYS;
  }

  @Override
  public Integer constraintWeight() {
    return Optional.ofNullable(weight).get();
  }

  protected DayOfWeek determineDayOfWeek(LocalDate date) {
    return of(date.get(ChronoField.DAY_OF_WEEK));
  }
}
