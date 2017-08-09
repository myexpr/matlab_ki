package _con.text.hotel.model;

import static _con.text.hotel.engine.SearchType.BUSINESS;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.asList;
import static java.util.Date.from;
import static org.junit.Assert.assertEquals;

import _con.text.hotel.constraint.business.OverWeekday;
import _con.text.hotel.engine.ContextEngine;
import _con.text.hotel.engine.SearchType;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;

public class ContextEngineTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final Room A_ROOM_FOR_SMALL_FAMILY = new Room(2, 2);
  private static final Room AN_ADULT_ACCOMPANYING_CHILDREN = new Room(2, 2);


  private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
  private static final LocalDate DAY_AFTER_TOMORROW = LocalDate.now().plusDays(2);

  @Test
  public void evaluatedAsBusinessTrip() {
    ContextEngine engine = new ContextEngine();
    SearchRequest request = new SearchRequest(A_DESTINATION, TOMORROW, DAY_AFTER_TOMORROW,
        asList(A_ROOM_FOR_ONE_ADULT));

    System.out.println(TOMORROW);
    System.out.println(DAY_AFTER_TOMORROW);
    System.out.println(new OverWeekday().evaluate(request));
    List<SearchType> evaluatedType = engine.evaluate(request);
    assertEquals(asList(BUSINESS), evaluatedType);
  }

}
