package context.hotel.model;

import static context.hotel.model.OccupancyType.BUSINESS_TRIP;
import static context.hotel.model.OccupancyType.WEEKEND_GETAWAY_ADULTS;
import static context.hotel.model.OccupancyType.WEEKEND_GETAWAY_FAMILIES;
import static context.hotel.model.OccupancyType.WEEKEND_GETAWAY_NUCLEAR_FAMILY;
import static java.lang.Boolean.FALSE;
import static java.lang.Integer.valueOf;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import context.hotel.model.response.OccupancyTypeMatch;
import java.time.LocalDate;
import org.junit.Test;


public class OccupancyTypeTest {

  LocalDate monday = now().with(firstDayOfNextMonth()).with(firstInMonth(MONDAY));
  LocalDate wednesday = monday.plusDays(2);
  LocalDate wednesdayOfNextWeek = monday.plusDays(9);
  LocalDate firstFriday = now().with(firstDayOfNextMonth()).with(firstInMonth(FRIDAY));
  LocalDate followingSunday = firstFriday.plusDays(2);

  @Test
  public void testProbabilisticMatchToMultipleSearchTypes() {
    Room roomWithKids = new Room(2, 2);
    SearchRequest request = new SearchRequest("A_DESTINATION", firstFriday, followingSunday,
        roomWithKids, null);

    assertFalse(WEEKEND_GETAWAY_ADULTS.evaluate(request));
    assertFalse(WEEKEND_GETAWAY_FAMILIES.evaluate(request));
    assertTrue(WEEKEND_GETAWAY_NUCLEAR_FAMILY.evaluate(request));

    OccupancyTypeMatch p75 = new OccupancyTypeMatch(WEEKEND_GETAWAY_ADULTS, valueOf(75));
    OccupancyTypeMatch p100 = new OccupancyTypeMatch(WEEKEND_GETAWAY_NUCLEAR_FAMILY, valueOf(100));
    assertEquals(p75,
        WEEKEND_GETAWAY_ADULTS.probabilisticEvaluation(request));
    assertEquals(p100,
        WEEKEND_GETAWAY_NUCLEAR_FAMILY.probabilisticEvaluation(request));
  }

  @Test
  public void testWeightedMatchToOccupancyType() {
    Room room = new Room(1, 0);
    SearchRequest stayFor9Days = new SearchRequest("DOESNT_MATTER", monday, wednesdayOfNextWeek,
        room, new NullSafeUser());

    OccupancyTypeMatch p75 = new OccupancyTypeMatch(BUSINESS_TRIP, 75);
    OccupancyTypeMatch p90 = new OccupancyTypeMatch(BUSINESS_TRIP, 90);

    assertEquals(FALSE, BUSINESS_TRIP.evaluate(stayFor9Days));
    assertEquals(p75, BUSINESS_TRIP.probabilisticEvaluation(stayFor9Days));
    assertEquals(p90, BUSINESS_TRIP.weightedEvaluation(stayFor9Days));
  }

  @Test
  public void weightedMatchShouldGiveMoreWeightToBusinessTrip() {
    Room room = new Room(1, 0);
    SearchRequest stayFor2Days = new SearchRequest("DOESNT_MATTER", monday, wednesday,
        room, new NullSafeUser());

    OccupancyTypeMatch p100 = new OccupancyTypeMatch(BUSINESS_TRIP, 100);
    OccupancyTypeMatch p50 = new OccupancyTypeMatch(WEEKEND_GETAWAY_ADULTS, 50);
    OccupancyTypeMatch p75 = new OccupancyTypeMatch(WEEKEND_GETAWAY_ADULTS, 75);
    OccupancyTypeMatch p40 = new OccupancyTypeMatch(WEEKEND_GETAWAY_NUCLEAR_FAMILY, 40);

    assertTrue(BUSINESS_TRIP.evaluate(stayFor2Days));
    assertEquals(p100, BUSINESS_TRIP.probabilisticEvaluation(stayFor2Days));
    assertEquals(p75, WEEKEND_GETAWAY_ADULTS.probabilisticEvaluation(stayFor2Days));
    assertEquals(p100, BUSINESS_TRIP.weightedEvaluation(stayFor2Days));
    assertEquals(p50, WEEKEND_GETAWAY_ADULTS.weightedEvaluation(stayFor2Days));
    assertEquals(p40, WEEKEND_GETAWAY_NUCLEAR_FAMILY.weightedEvaluation(stayFor2Days));
  }

}
