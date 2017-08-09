package _con.text.hotel.model.constraint.business;

import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import _con.text.hotel.constraint.business.OverWeekday;
import _con.text.hotel.model.Room;
import _con.text.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;

/**
 * Created by araman on 09/08/2017.
 */
public class OverWeekdayTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final SearchRequest A_WEEKDAY_REQUEST = new SearchRequest(A_DESTINATION, nextMonthsFirst(MONDAY),
      nextMonthsFirst(MONDAY).plusDays(4), A_ROOM_FOR_ONE_ADULT);

  private static final SearchRequest NOT_A_WEEKDAY_REQUEST = new SearchRequest(A_DESTINATION,
      nextMonthsFirst(FRIDAY), nextMonthsFirst(FRIDAY).plusDays(1), A_ROOM_FOR_ONE_ADULT);

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

  private static LocalDate nextMonthsFirst(DayOfWeek dayOfWeek) {
    return LocalDate.now().with(firstDayOfNextMonth()).with(firstInMonth(dayOfWeek));
  }


}
