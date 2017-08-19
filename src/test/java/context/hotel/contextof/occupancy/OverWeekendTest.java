package context.hotel.contextof.occupancy;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class OverWeekendTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final SearchRequest NOT_A_WEEKDAY_REQUEST = new SearchRequest(A_DESTINATION,
      nextMonthsFirst(FRIDAY), nextMonthsFirst(FRIDAY).plusDays(2), A_ROOM_FOR_ONE_ADULT, null);

  @Test
  public void isAWeekendRequest() {
    OverWeekend overWeekend = new OverWeekend();
    Assert.assertTrue(overWeekend.evaluate(NOT_A_WEEKDAY_REQUEST));
  }

  private static LocalDate nextMonthsFirst(DayOfWeek dayOfWeek) {
    return LocalDate.now().with(firstDayOfNextMonth()).with(firstInMonth(dayOfWeek));
  }


}
