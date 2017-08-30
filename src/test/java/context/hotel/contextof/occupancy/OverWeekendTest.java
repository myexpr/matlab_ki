package context.hotel.contextof.occupancy;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Test;

public class OverWeekendTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final SearchRequest NOT_A_WEEKEND_REQUEST = new SearchRequest(A_DESTINATION,
      nextMonthsFirst(FRIDAY), nextMonthsFirst(FRIDAY).plusDays(2), A_ROOM_FOR_ONE_ADULT, null);
  private static final SearchRequest A_WEEKEND_REQUEST = new SearchRequest(A_DESTINATION,
      nextMonthsFirst(SATURDAY), nextMonthsFirst(SATURDAY).plusDays(1), A_ROOM_FOR_ONE_ADULT, null);
  private static final SearchRequest A_REQUEST_OVERWEEKEND = new SearchRequest(A_DESTINATION,
      nextMonthsFirst(SATURDAY), nextMonthsFirst(SATURDAY).plusDays(5), A_ROOM_FOR_ONE_ADULT, null);


  @Test
  public void isNotAWeekendRequest() {
    OverWeekend overWeekend = new OverWeekend();
    assertFalse(overWeekend.evaluate(NOT_A_WEEKEND_REQUEST));
  }

  @Test
  public void isAWeekendRequest() {
    OverWeekend overWeekend = new OverWeekend();
    assertTrue(overWeekend.evaluate(A_WEEKEND_REQUEST));
  }

  @Test
  public void stillNotAWeekendRequestWhenItOverLapsWeekend() {
    OverWeekend overWeekend = new OverWeekend();
    assertFalse(overWeekend.evaluate(A_REQUEST_OVERWEEKEND));
    assertFalse(new OverWeekday().evaluate(A_REQUEST_OVERWEEKEND));
  }

  private static LocalDate nextMonthsFirst(DayOfWeek dayOfWeek) {
    return LocalDate.now().with(firstDayOfNextMonth()).with(firstInMonth(dayOfWeek));
  }


}
