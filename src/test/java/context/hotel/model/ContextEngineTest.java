package context.hotel.model;

import static context.hotel.engine.SearchType.BUSINESS_TRIP;
import static context.hotel.engine.SearchType.VACATION_NUCLEAR_FAMILY;
import static context.hotel.engine.SearchType.WEEKEND_GETAWAY_ADULTS;
import static context.hotel.engine.SearchType.WEEKEND_GETAWAY_NUCLEAR_FAMILY;
import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import context.hotel.engine.ContextEngine;
import context.hotel.engine.PartialMatch;
import context.hotel.engine.SearchType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;

public class ContextEngineTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final Room A_ROOM_FOR_SMALL_FAMILY = new Room(2, 2);
  private static final Room AN_ADULT_ACCOMPANYING_CHILDREN = new Room(2, 2);

  private static final LocalDate TOMORROW = nextMonthsFirst(DayOfWeek.TUESDAY);
  private static final LocalDate DAY_AFTER_TOMORROW = TOMORROW.plusDays(3);

  @Test
  public void evaluatedAsBusinessTrip() {
    ContextEngine engine = new ContextEngine();
    SearchRequest request = new SearchRequest(A_DESTINATION, TOMORROW, DAY_AFTER_TOMORROW,
        asList(A_ROOM_FOR_ONE_ADULT));
    SearchType evaluatedType = engine.deterministicEvaluation(request);
    assertEquals(BUSINESS_TRIP, evaluatedType);
  }

  @Test
  public void notABusinessTrip() {
    ContextEngine engine = new ContextEngine();
    LocalDate nextMonthsFirstSaturday = LocalDate.now()
        .with(firstDayOfNextMonth())
        .with(firstInMonth(DayOfWeek.SATURDAY));
    SearchRequest request = new SearchRequest(A_DESTINATION, nextMonthsFirstSaturday,
        nextMonthsFirstSaturday.plusDays(5),
        asList(A_ROOM_FOR_ONE_ADULT));
    SearchType evaluatedType = engine.deterministicEvaluation(request);
    assertEquals(WEEKEND_GETAWAY_ADULTS, evaluatedType);
  }

  @Test
  public void multipleProbableMatches() {
    ContextEngine engine = new ContextEngine();
    LocalDate firstFriday = now().with(firstDayOfNextMonth()).with(firstInMonth(DayOfWeek.FRIDAY));
    LocalDate followingSunday = firstFriday.plusDays(2);
    Room roomWithKids = new Room(2, 2);
    SearchRequest request = new SearchRequest("A_DESTINATION", firstFriday, followingSunday,
        roomWithKids);

    List<PartialMatch> partialMatches = asList(
        new PartialMatch(WEEKEND_GETAWAY_ADULTS, 75),
        new PartialMatch(WEEKEND_GETAWAY_NUCLEAR_FAMILY, 100),
        new PartialMatch(VACATION_NUCLEAR_FAMILY, 75));
    assertEquals(partialMatches, engine.partialMatches(request));
  }


  private static LocalDate nextMonthsFirst(DayOfWeek dayOfWeek) {
    return LocalDate.now().with(firstDayOfNextMonth()).with(firstInMonth(dayOfWeek));
  }

}
