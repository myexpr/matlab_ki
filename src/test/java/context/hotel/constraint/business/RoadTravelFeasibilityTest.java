package context.hotel.constraint.business;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.Feasibility.REASONABLE_STRETCH;

import context.hotel.model.InfeasibleRoute;
import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.User;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by araman on 19/08/2017.
 */
public class RoadTravelFeasibilityTest {

  SearchRequest A_SEARCH_WITH_NO_CHILDREN = new SearchRequest("343", LocalDate.now(),
      LocalDate.now().plusDays(2), new Room(2, 0), new User());

  SearchRequest A_SEARCH_WITH_2_CHILDREN = new SearchRequest("343", LocalDate.now(),
      LocalDate.now().plusDays(2), new Room(2, 2), new User());

  @Test
  public void returnInfeasibleWhenInfeasibleRoute() {
    TimeDistance td = new InfeasibleRoute();
    SearchRequest searchRequest = A_SEARCH_WITH_2_CHILDREN;
    Assert.assertEquals(INFEASIBLE, new RoadTravelFeasibility().roadFeasibility(td, searchRequest));
  }

  @Test
  public void returnPreferredWhenDistanceLessThan500() {
    TimeDistance td = new TimeDistance(430, 7200);
    SearchRequest searchRequest = null;
    Assert.assertEquals(PREFERRED, new RoadTravelFeasibility().roadFeasibility(td, searchRequest));
  }

  @Test
  public void returnStretchForDistanceBetween500_800_withNochildren() {
    TimeDistance td = new TimeDistance(600, 14400);
    SearchRequest searchRequest = A_SEARCH_WITH_NO_CHILDREN;
    Assert.assertEquals(REASONABLE_STRETCH, new RoadTravelFeasibility().roadFeasibility(td, searchRequest));
  }

  @Test
  public void returnStretchForDistanceBetween500_800_withchildren() {
    TimeDistance td = new TimeDistance(600, 14400);
    SearchRequest searchRequest = A_SEARCH_WITH_2_CHILDREN;
    Assert.assertEquals(DIFFICULT, new RoadTravelFeasibility().roadFeasibility(td, searchRequest));
  }

  @Test
  public void distancesGreaterThan800_WithNoChildren_AreDifficult() {
    TimeDistance td = new TimeDistance(1200, 1456400);
    SearchRequest searchRequest = A_SEARCH_WITH_NO_CHILDREN;
    Assert.assertEquals(DIFFICULT, new RoadTravelFeasibility().roadFeasibility(td, searchRequest));
  }

  @Test
  public void distancesGreaterThan800_WithChildren_IsInfeasible() {
    TimeDistance td = new TimeDistance(1200, 1456400);
    SearchRequest searchRequest = A_SEARCH_WITH_2_CHILDREN;
    Assert.assertEquals(INFEASIBLE, new RoadTravelFeasibility().roadFeasibility(td, searchRequest));
  }

}
