package context.hotel.model;

import static context.hotel.model.SearchType.WEEKEND_GETAWAY_ADULTS;
import static context.hotel.model.SearchType.WEEKEND_GETAWAY_FAMILIES;
import static context.hotel.model.SearchType.WEEKEND_GETAWAY_NUCLEAR_FAMILY;
import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import context.hotel.model.response.SearchTypeMatch;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.Test;


public class SearchTypeTest {

  @Test
  public void testProbabilisticMatchToMultipleSearchTypes() {
    LocalDate firstFriday = now().with(firstDayOfNextMonth()).with(firstInMonth(DayOfWeek.FRIDAY));
    LocalDate followingSunday = firstFriday.plusDays(2);
    Room roomWithKids = new Room(2, 2);
    SearchRequest request = new SearchRequest("A_DESTINATION", firstFriday, followingSunday,
        roomWithKids, null);

    assertFalse(WEEKEND_GETAWAY_ADULTS.evaluate(request));
    assertFalse(WEEKEND_GETAWAY_FAMILIES.evaluate(request));
    assertTrue(WEEKEND_GETAWAY_NUCLEAR_FAMILY.evaluate(request));

    assertEquals(new SearchTypeMatch(WEEKEND_GETAWAY_ADULTS, new Integer(75)),
        WEEKEND_GETAWAY_ADULTS.probabilisticEvaluation(request));
    assertEquals(new SearchTypeMatch(WEEKEND_GETAWAY_NUCLEAR_FAMILY, new Integer(100)),
        WEEKEND_GETAWAY_NUCLEAR_FAMILY.probabilisticEvaluation(request));
  }

}
