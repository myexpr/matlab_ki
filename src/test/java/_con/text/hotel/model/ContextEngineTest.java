package _con.text.hotel.model;

import static _con.text.hotel.engine.SearchType.BUSINESS;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.*;
import static java.util.Arrays.asList;
import static java.util.Date.from;
import static org.junit.Assert.assertEquals;

import _con.text.hotel.engine.ContextEngine;
import _con.text.hotel.engine.SearchType;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Test;

public class ContextEngineTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final Room A_ROOM_FOR_SMALL_FAMILY = new Room(2, 2);
  private static final Room AN_ADULT_ACCOMPANYING_CHILDREN = new Room(2, 2);

  private static final Instant TOMORROW_AS_INSTANT = now().plus(1, DAYS).truncatedTo(DAYS);
  private static final Date TOMORROW = from(TOMORROW_AS_INSTANT);
  private static final Date DAY_AFTER_TOMORROW = from(TOMORROW_AS_INSTANT.plus(1, DAYS));

  @Test
  public void evaluatedAsBusinessTrip() {
    ContextEngine engine = new ContextEngine();
    SearchRequest request = new SearchRequest(A_DESTINATION, TOMORROW, DAY_AFTER_TOMORROW,
        asList(A_ROOM_FOR_ONE_ADULT));
    List<SearchType> evaluatedType = engine.evaluate(request);
    assertEquals(asList(BUSINESS), evaluatedType);
  }


}
