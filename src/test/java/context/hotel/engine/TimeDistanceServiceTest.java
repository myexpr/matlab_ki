package context.hotel.engine;

import static context.hotel.model.TravelMode.ROAD;
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
public class TimeDistanceServiceTest {

  @Test
  public void givenDestinationRetrieveDrivingTimeDurationForFeasibleRoutes() {
    Destination destination = new Destination("22547", "Bath", "", "GB", 51.3794, -2.3656);
    User aUser = new User(new GeoCoordinate(51.5141, -0.0937));
    SearchRequest searchRequest = new SearchRequest(null, null, null, (Room) null, aUser);

    TimeDistanceService service = new TimeDistanceService();
    TimeDistance td = service
        .timeAndDistanceTo(searchRequest.assumedUserOrigin(), destination.name(), ROAD);

    assertTrue(td.getDistance() > 5000);
    assertTrue(td.getTime() > 7200);
  }

  @Test
  public void noFeasibleRoutesExistForSomeSourceDestination() {
    Destination destination = new Destination("22547", "Delhi", "", "IN", 28.6167, 77.2167);
    User aUser = new User(new GeoCoordinate(51.5141, -0.0937));
    SearchRequest searchRequest = new SearchRequest(null, null, null, (Room) null, aUser);

    TimeDistanceService service = new TimeDistanceService();
    TimeDistance td = service
        .timeAndDistanceTo(searchRequest.assumedUserOrigin(), destination.name(), ROAD);

    assertEquals(td.getClass(), InfeasibleRoute.class);
  }


}
