package context.hotel.contextof.travelmode;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.Feasibility.REASONABLE_STRETCH;
import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import context.hotel.model.Destination;
import context.hotel.model.GeoCoordinate;
import context.hotel.model.InfeasibleRoute;
import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.User;
import org.junit.Test;

/**
 * Created by araman on 19/08/2017.
 */
public class RoadContextFragmentTest {

  SearchRequest A_SEARCH_WITH_NO_CHILDREN = new SearchRequest("343", now(),
      now().plusDays(2), new Room(2, 0), new User());

  SearchRequest A_SEARCH_WITH_2_CHILDREN = new SearchRequest("343", now(),
      now().plusDays(2), new Room(2, 2), new User());
  private int mpkm = 1000;

  @Test
  public void returnInfeasibleWhenInfeasibleRoute() {
    TimeDistance td = new InfeasibleRoute();
    SearchRequest searchRequest = A_SEARCH_WITH_2_CHILDREN;
    assertEquals(INFEASIBLE, new Road().determineFeasibility(td, searchRequest));
  }

  @Test
  public void returnPreferredWhenDistanceLessThan500() {
    TimeDistance td = new TimeDistance(430 * mpkm, 7200);
    SearchRequest searchRequest = null;
    assertEquals(PREFERRED, new Road().determineFeasibility(td, searchRequest));
  }

  @Test
  public void returnStretchForDistanceBetween500_800_withNoChildren() {
    TimeDistance td = new TimeDistance(600 * mpkm, 14400);
    SearchRequest searchRequest = A_SEARCH_WITH_NO_CHILDREN;
    assertEquals(REASONABLE_STRETCH,
        new Road().determineFeasibility(td, searchRequest));
  }

  @Test
  public void returnStretchForDistanceBetween500_800_withChildren() {
    TimeDistance td = new TimeDistance(600 * mpkm, 14400);
    SearchRequest searchRequest = A_SEARCH_WITH_2_CHILDREN;
    assertEquals(DIFFICULT, new Road().determineFeasibility(td, searchRequest));
  }

  @Test
  public void distancesGreaterThan800_WithNoChildren_AreDifficult() {
    TimeDistance td = new TimeDistance(1200 * mpkm, 1456400);
    SearchRequest searchRequest = A_SEARCH_WITH_NO_CHILDREN;
    assertEquals(DIFFICULT, new Road().determineFeasibility(td, searchRequest));
  }

  @Test
  public void distancesGreaterThan800_WithChildren_IsInfeasible() {
    TimeDistance td = new TimeDistance(1200 * mpkm, 1456400);
    SearchRequest searchRequest = A_SEARCH_WITH_2_CHILDREN;
    assertEquals(INFEASIBLE, new Road().determineFeasibility(td, searchRequest));
  }

  @Test
  public void givenDestinationRetrieveDrivingTimeDurationForFeasibleRoutes() {
    Destination destination = new Destination("22547", "Bath", "", "GB", 51.3794, -2.3656);
    User aUser = new User(new GeoCoordinate(51.5141, -0.0937));
    SearchRequest searchRequest = new SearchRequest(null, null, null, (Room) null, aUser);
    searchRequest.setResolvedDestination(destination);

    Travel service = new Road();
    TimeDistance td = service.determineTimeDistance(searchRequest);
    System.out.println(">>>>>" + td);
    assertTrue(td.getDistance() > 5000);
    assertTrue(td.getTime() > 7200);
  }

  @Test
  public void noFeasibleRoutesExistForSomeSourceDestination() {
    Destination destination = new Destination("22547", "Delhi", "", "IN", 28.6167, 77.2167);
    User aUser = new User(new GeoCoordinate(51.5141, -0.0937));
    SearchRequest searchRequest = new SearchRequest(null, null, null, (Room) null, aUser);
    searchRequest.setResolvedDestination(destination);

    Travel service = new Road();
    TimeDistance td = service.determineTimeDistance(searchRequest);

    assertEquals(td.getClass(), InfeasibleRoute.class);
  }

  @Test
  public void noFeasibleRoutesExistIfCustomerLocationNotKnown() {
    Destination destination = new Destination("22547", "Delhi", "", "IN", 28.6167, 77.2167);
    User aUser = new User();
    SearchRequest searchRequest = new SearchRequest("22547", now(), now().plusDays(5), (Room) null,
        aUser);
    searchRequest.setResolvedDestination(destination);

    Travel service = new Road();
    TimeDistance td = service.determineTimeDistance(searchRequest);

    assertEquals(td.getClass(), InfeasibleRoute.class);
  }


}
