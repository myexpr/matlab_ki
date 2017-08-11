package _con.text.hotel.model;

import static _con.text.hotel.engine.SearchType.BUSINESS_TRIP;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import _con.text.hotel.constraint.business.BusinessConstraints;
import _con.text.hotel.engine.ContextEngine;
import _con.text.hotel.engine.SearchType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
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
    List<SearchType> evaluatedType = engine.evaluate(request);
    assertEquals(asList(BUSINESS_TRIP), evaluatedType);
  }

  @Test
  public void notABusinessTrip() {
    ContextEngine engine = new ContextEngine();
    LocalDate nextMonthsFirstSaturday = LocalDate.now()
        .with(TemporalAdjusters.firstDayOfNextMonth())
        .with(TemporalAdjusters.firstInMonth(
            DayOfWeek.SATURDAY));
    SearchRequest request = new SearchRequest(A_DESTINATION, nextMonthsFirstSaturday,
        nextMonthsFirstSaturday.plusDays(5),
        asList(A_ROOM_FOR_ONE_ADULT));
    List<SearchType> evaluatedType = engine.evaluate(request);
    List<BusinessConstraints> emptyConstraint = Collections.emptyList();
    assertEquals(emptyConstraint, evaluatedType);
  }

  private static LocalDate nextMonthsFirst(DayOfWeek dayOfWeek) {
    return LocalDate.now().with(firstDayOfNextMonth()).with(firstInMonth(dayOfWeek));
  }


}
