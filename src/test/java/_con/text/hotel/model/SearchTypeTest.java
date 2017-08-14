package _con.text.hotel.model;

import static _con.text.hotel.engine.SearchType.VACATION_NUCLEAR_FAMILY;
import static _con.text.hotel.engine.SearchType.WEEKEND_GETAWAY_ADULTS;
import static _con.text.hotel.engine.SearchType.WEEKEND_GETAWAY_FAMILIES;
import static _con.text.hotel.engine.SearchType.WEEKEND_GETAWAY_NUCLEAR_FAMILY;
import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import _con.text.hotel.engine.ProbabilityMatch;
import _con.text.hotel.engine.SearchType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;


public class SearchTypeTest {

  @Test
  public void testProbabilisticMatchToMultipleSearchTypes() {
    LocalDate firstFriday = now().with(firstDayOfNextMonth()).with(firstInMonth(DayOfWeek.FRIDAY));
    LocalDate followingSunday = firstFriday.plusDays(2);
    Room roomWithKids = new Room(2, 2);
    SearchRequest request = new SearchRequest("A_DESTINATION", firstFriday, followingSunday,
        roomWithKids);

    assertFalse(WEEKEND_GETAWAY_ADULTS.evaluate(request));
    assertFalse(WEEKEND_GETAWAY_FAMILIES.evaluate(request));
    assertTrue(WEEKEND_GETAWAY_NUCLEAR_FAMILY.evaluate(request));

    Map<SearchType, Integer> matchedTypes = new HashMap<>();
    matchedTypes.put(WEEKEND_GETAWAY_ADULTS, 75);
    matchedTypes.put(WEEKEND_GETAWAY_NUCLEAR_FAMILY, 100);
    matchedTypes.put(VACATION_NUCLEAR_FAMILY, 75);

    assertEquals(new ProbabilityMatch(WEEKEND_GETAWAY_ADULTS, new Integer(75)), WEEKEND_GETAWAY_ADULTS.probabilisticEvaluation(request));
    assertEquals(new ProbabilityMatch(WEEKEND_GETAWAY_NUCLEAR_FAMILY, new Integer(100)), WEEKEND_GETAWAY_NUCLEAR_FAMILY.probabilisticEvaluation(request));
  }

}