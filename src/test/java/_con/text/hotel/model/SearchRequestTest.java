package _con.text.hotel.model;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.asList;
import static java.util.Date.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import org.junit.Test;

public class SearchRequestTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final Room A_ROOM_FOR_SMALL_FAMILY = new Room(2, 2);
  private static final Room AN_ADULT_ACCOMPANYING_CHILDREN = new Room(2, 2);

  private static final Instant TOMORROW = now().plus(1, DAYS).truncatedTo(DAYS);
  private static final Date FROM_DATE = from(TOMORROW);
  private static final Date FROM_PLUS_2_DAYS = from(TOMORROW.plus(2, DAYS));

  @Test
  public void requestIsFor2Days() {
    SimpleSearchRequest request = new SimpleSearchRequest(A_DESTINATION, FROM_DATE, FROM_PLUS_2_DAYS,
        asList(A_ROOM_FOR_ONE_ADULT));
    assertEquals(2, request.numberOfNights());
  }

  @Test
  public void requestIsFor1Person() {
    SimpleSearchRequest request = new SimpleSearchRequest(A_DESTINATION, FROM_DATE, FROM_PLUS_2_DAYS,
        asList(A_ROOM_FOR_ONE_ADULT));
    assertTrue(request.singleOccupancy());
    assertFalse(request.partyWithChildren());
  }

  @Test
  public void requestIsForAFamily() {
    SimpleSearchRequest request = new SimpleSearchRequest(A_DESTINATION, FROM_DATE, FROM_PLUS_2_DAYS,
        asList(A_ROOM_FOR_SMALL_FAMILY));
    assertFalse(request.singleOccupancy());
    assertTrue(request.partyWithChildren());

    SimpleSearchRequest anotherRequest = new SimpleSearchRequest(A_DESTINATION, FROM_DATE, FROM_PLUS_2_DAYS,
        asList(AN_ADULT_ACCOMPANYING_CHILDREN));
    assertFalse(anotherRequest.singleOccupancy());
    assertTrue(anotherRequest.partyWithChildren());
  }

}

