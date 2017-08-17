package context.hotel.model;

import static context.hotel.engine.SearchType.WEEKEND_GETAWAY_ADULTS;
import static context.hotel.engine.SearchType.WEEKEND_GETAWAY_FAMILIES;
import static context.hotel.engine.SearchType.WEEKEND_GETAWAY_NUCLEAR_FAMILY;
import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import context.hotel.engine.PartialMatch;
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
        roomWithKids);

    assertFalse(WEEKEND_GETAWAY_ADULTS.evaluate(request));
    assertFalse(WEEKEND_GETAWAY_FAMILIES.evaluate(request));
    assertTrue(WEEKEND_GETAWAY_NUCLEAR_FAMILY.evaluate(request));

    assertEquals(new PartialMatch(WEEKEND_GETAWAY_ADULTS, new Integer(75)),
        WEEKEND_GETAWAY_ADULTS.probabilisticEvaluation(request));
    assertEquals(new PartialMatch(WEEKEND_GETAWAY_NUCLEAR_FAMILY, new Integer(100)),
        WEEKEND_GETAWAY_NUCLEAR_FAMILY.probabilisticEvaluation(request));
  }

  public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

  public int calculateDistanceInKilometer(double userLat, double userLng,
      double venueLat, double venueLng) {

    double latDistance = Math.toRadians(userLat - venueLat);
    double lngDistance = Math.toRadians(userLng - venueLng);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
        * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
  }

  @Test
  public void testSomeDistances() {
    Double latA, latB, longA, longB;
    latA = 52.5597;
    longA = 13.2877;
    latB = 51.4706;
    longB = -0.461941;

    System.out.println(calculateDistanceInKilometer(latA, longA, latB, longB));
  }


}
