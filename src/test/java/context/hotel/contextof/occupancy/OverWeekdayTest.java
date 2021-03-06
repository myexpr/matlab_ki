package context.hotel.contextof.occupancy;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalDate.parse;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import context.hotel.model.NullSafeUser;
import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Test;

/**
 * Created by araman on 09/08/2017.
 */
public class OverWeekdayTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final SearchRequest A_WEEKDAY_REQUEST = new SearchRequest(A_DESTINATION,
      nextMonthsFirst(MONDAY),
      nextMonthsFirst(MONDAY).plusDays(4), A_ROOM_FOR_ONE_ADULT, null);

  private static final SearchRequest NOT_A_WEEKDAY_REQUEST = new SearchRequest(A_DESTINATION,
      nextMonthsFirst(FRIDAY), nextMonthsFirst(FRIDAY).plusDays(1), A_ROOM_FOR_ONE_ADULT, null);

  @Test
  public void requestIsExclusivelyOverWeekdays() {
    OverWeekday weekdayConstraint = new OverWeekday();
    assertTrue(weekdayConstraint.evaluate(A_WEEKDAY_REQUEST));
  }

  @Test
  public void requestIsNotExclusivelyOverWeekdays() {
    OverWeekday weekdayConstraint = new OverWeekday();
    System.out.println(NOT_A_WEEKDAY_REQUEST);
    assertFalse(weekdayConstraint.evaluate(NOT_A_WEEKDAY_REQUEST));
  }

  @Test
  public void requestIsNeitherOverWeekdayOrOverWeekEnd() {
    OverWeekday weekdayConstraint = new OverWeekday();
    SearchRequest aRequestOverLappingWeekend = new SearchRequest("FOO", parse("2017-08-30"), parse("2017-09-04"), new Room(2,0), new NullSafeUser());
    SearchRequest aRequestNotOverLappingWeekend = new SearchRequest("FOO", parse("2017-08-30"), parse("2017-09-01"), new Room(2,0), new NullSafeUser());
    assertFalse(weekdayConstraint.evaluate(aRequestOverLappingWeekend));
    assertTrue(weekdayConstraint.evaluate(aRequestNotOverLappingWeekend));
  }


  private static LocalDate nextMonthsFirst(DayOfWeek dayOfWeek) {
    return LocalDate.now().with(firstDayOfNextMonth()).with(firstInMonth(dayOfWeek));
  }


}
