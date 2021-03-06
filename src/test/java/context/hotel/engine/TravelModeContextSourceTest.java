package context.hotel.engine;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.TravelMode.RAIL;
import static context.hotel.model.TravelMode.ROAD;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import context.hotel.model.Destination;
import context.hotel.model.Feasibility;
import context.hotel.model.GeoCoordinate;
import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import context.hotel.model.TravelMode;
import context.hotel.model.User;
import context.hotel.model.response.TravelModeMatch;
import context.hotel.repository.DestinationRepository;
import context.hotel.source.TravelModeContextSource;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by araman on 19/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelModeContextSourceTest {

  @Autowired
  TravelModeContextSource travelModeContextService;

  @Autowired
  DestinationRepository destinationRepository;

  GeoCoordinate LONDON = new GeoCoordinate(51.5141, -0.0937);
  GeoCoordinate EDINBURG = new GeoCoordinate(55.9500, -3.2000);
  SearchRequest A_SEARCH_TO_BATH_FROM_LONDON_WITH_NO_CHILDREN = new SearchRequest("22547",
      LocalDate.now().plusDays(7),
      LocalDate.now().plusDays(10), new Room(2, 0), new User(LONDON));
  SearchRequest A_SEARCH_TO_BATH_FROM_EDINB_WITH_NO_CHILDREN = new SearchRequest("32383",
      LocalDate.now().plusDays(7),
      LocalDate.now().plusDays(10), new Room(2, 0), new User(EDINBURG));
  SearchRequest A_SEARCH_TO_DELHI_FROM_LONDON_WITH_NO_CHILDREN = new SearchRequest("25833",
      LocalDate.now().plusDays(7),
      LocalDate.now().plusDays(10), new Room(2, 0), new User(LONDON));

  @Before
  public void setup() {
    Destination d = destinationRepository.findOne("22547");
    A_SEARCH_TO_BATH_FROM_EDINB_WITH_NO_CHILDREN.setResolvedDestination(d);
    A_SEARCH_TO_BATH_FROM_LONDON_WITH_NO_CHILDREN.setResolvedDestination(d);

    d = destinationRepository.findOne("25833");
    A_SEARCH_TO_DELHI_FROM_LONDON_WITH_NO_CHILDREN.setResolvedDestination(d);
  }

  @Test
  public void road_and_rail_should_be_Preferred_TravelMode_for_london_bath() {
    List<TravelModeMatch> feasibleTravelModes = travelModeContextService
        .deriveContext(A_SEARCH_TO_BATH_FROM_LONDON_WITH_NO_CHILDREN);
    List<TravelMode> expectedPreferredTravelModes = asList(ROAD, RAIL);
    List<TravelMode> actualTravelModes = getTravelModesFromMatchesOfType(feasibleTravelModes,
        PREFERRED);
    assertEquals(expectedPreferredTravelModes, actualTravelModes);
  }

  @Test
  public void rail_should_be_difficult_TravelMode_for_edinburgh_bath() {
    List<TravelModeMatch> feasibleTravelModes = travelModeContextService
        .deriveContext(A_SEARCH_TO_BATH_FROM_EDINB_WITH_NO_CHILDREN);
    List<TravelMode> expectedPreferredTravelModes = asList(RAIL);
    List<TravelMode> actualTravelModes = getTravelModesFromMatchesOfType(feasibleTravelModes,
        DIFFICULT);
    assertEquals(expectedPreferredTravelModes, actualTravelModes);
  }

  @Test
  public void rail_road_should_be_infeasible_TravelMode_for_london_delhi() {
    List<TravelModeMatch> feasibleTravelModes = travelModeContextService
        .deriveContext(A_SEARCH_TO_DELHI_FROM_LONDON_WITH_NO_CHILDREN);
    System.out.println(feasibleTravelModes);
    List<TravelMode> expectedPreferredTravelModes = asList(ROAD, RAIL);
    List<TravelMode> actualTravelModes = getTravelModesFromMatchesOfType(feasibleTravelModes,
        INFEASIBLE);
    assertEquals(expectedPreferredTravelModes, actualTravelModes);
  }

  List<TravelMode> getTravelModesFromMatchesOfType(List<TravelModeMatch> feasibleTravelModes,
      Feasibility feasibility) {
    return feasibleTravelModes
        .stream()
        .filter((TravelModeMatch t) -> t.getFeasibility().equals(feasibility))
        .map(TravelModeMatch::getTravelMode)
        .collect(toList());
  }
}