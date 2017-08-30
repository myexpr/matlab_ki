package context.hotel.model.response;

import static context.hotel.model.OccupancyType.BUSINESS_TRIP;
import static context.hotel.model.OccupancyType.WEEKEND_GETAWAY_ADULTS;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by araman on 30/08/2017.
 */
public class OccupancyTypeMatchTest {

  @Test
  public void compare2MatchesOfSameType() {
    OccupancyTypeMatch a = new OccupancyTypeMatch(BUSINESS_TRIP, 55);
    OccupancyTypeMatch b = new OccupancyTypeMatch(BUSINESS_TRIP, 40) ;
    OccupancyTypeMatch c = new OccupancyTypeMatch(BUSINESS_TRIP, 90);

    List<OccupancyTypeMatch> actualSortedList = asList(a, b, c).stream().sorted().collect(toList());
    List<OccupancyTypeMatch> expectedOrder = asList(c, a, b);

    assertEquals(expectedOrder, actualSortedList);
  }

  @Test
  public void compare2MatchesOfDifferentType() {
    OccupancyTypeMatch a = new OccupancyTypeMatch(BUSINESS_TRIP, 55);
    OccupancyTypeMatch b = new OccupancyTypeMatch(WEEKEND_GETAWAY_ADULTS, 40) ;
    OccupancyTypeMatch c = new OccupancyTypeMatch(BUSINESS_TRIP, 90);

    List<OccupancyTypeMatch> actualSortedList = asList(a, b, c).stream().sorted().collect(toList());
    List<OccupancyTypeMatch> expectedOrder = asList(c, a, b);

    Assert.assertEquals(expectedOrder, actualSortedList);
  }


}