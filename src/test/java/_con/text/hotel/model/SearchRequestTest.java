package _con.text.hotel.model;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.junit.Test;

public class SearchRequestTest {

  private static final String A_DESTINATION = "FOO_BAR";
  private static final Room A_ROOM_FOR_ONE_ADULT = new Room(1, 0);
  private static final Room A_ROOM_FOR_SMALL_FAMILY = new Room(2, 2);
  private static final Room AN_ADULT_ACCOMPANYING_CHILDREN = new Room(2, 2);


  private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
  private static final LocalDate TOMORROW_PLUS_2_DAYS = LocalDate.now().plusDays(3);

  @Test
  public void requestIsFor2Days() {
    SearchRequest request = new SearchRequest(A_DESTINATION, TOMORROW, TOMORROW_PLUS_2_DAYS,
        asList(A_ROOM_FOR_ONE_ADULT));
    assertEquals(new Integer(2), request.numberOfNights());
  }

  @Test
  public void requestIsFor1Person() {
    SearchRequest request = new SearchRequest(A_DESTINATION, TOMORROW, TOMORROW_PLUS_2_DAYS,
        asList(A_ROOM_FOR_ONE_ADULT));
    assertTrue(request.singleOccupancy());
    assertFalse(request.partyWithChildren());
  }

  @Test
  public void requestIsForAFamily() {
    SearchRequest request = new SearchRequest(A_DESTINATION, TOMORROW, TOMORROW_PLUS_2_DAYS,
        asList(A_ROOM_FOR_SMALL_FAMILY));
    assertFalse(request.singleOccupancy());
    assertTrue(request.partyWithChildren());

    SearchRequest anotherRequest = new SearchRequest(A_DESTINATION, TOMORROW, TOMORROW_PLUS_2_DAYS,
        asList(AN_ADULT_ACCOMPANYING_CHILDREN));
    assertFalse(anotherRequest.singleOccupancy());
    assertTrue(anotherRequest.partyWithChildren());
  }

}

