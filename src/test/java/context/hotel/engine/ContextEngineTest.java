package context.hotel.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import context.hotel.model.Destination;
import context.hotel.model.GeoCoordinate;
import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import context.hotel.model.User;
import context.hotel.model.response.ContextMatch;
import context.hotel.repository.DestinationRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by araman on 19/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContextEngineTest {

  @Autowired
  ContextEngine contextEngine;
  @Autowired
  private DestinationRepository destinationRepository;
  @Value("${secret.facebook}")
  private String accessToken;

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
    Map<String, ? extends List<? extends ContextMatch>> aggregatedContext = contextEngine
        .process(A_SEARCH_TO_BATH_FROM_LONDON_WITH_NO_CHILDREN);
    asJson(A_SEARCH_TO_BATH_FROM_LONDON_WITH_NO_CHILDREN, aggregatedContext);

  }

  @Test
  public void rail_road_should_be_infeasible_TravelMode_for_london_delhi() {
    Map<String, ? extends List<? extends ContextMatch>> process = contextEngine
        .process(A_SEARCH_TO_DELHI_FROM_LONDON_WITH_NO_CHILDREN);
    asJson(A_SEARCH_TO_DELHI_FROM_LONDON_WITH_NO_CHILDREN, process);
  }

  @Test
  public void rail_road_should_be_infeasible_TravelMode_for_london_delhi_when_locaiton_determined_from_fb() {
    SearchRequest anIdenticalRequest = new SearchRequest("25833",  LocalDate.now().plusDays(7),
        LocalDate.now().plusDays(10), new Room(2, 0), new User(accessToken));
    Destination delhi = destinationRepository.findOne("25833");
    anIdenticalRequest.setResolvedDestination(delhi);
    Map<String, ? extends List<? extends ContextMatch>> process = contextEngine
        .process(anIdenticalRequest);
    asJson(anIdenticalRequest, process);
  }

  @Test
  public void unknown_user() {
    SearchRequest anIdenticalRequest = new SearchRequest("25833",  LocalDate.now().plusDays(7),
        LocalDate.now().plusDays(10), new Room(2, 0), new User());
    Destination delhi = destinationRepository.findOne("25833");
    anIdenticalRequest.setResolvedDestination(delhi);
    Map<String, ? extends List<? extends ContextMatch>> process = contextEngine
        .process(anIdenticalRequest);
    asJson(anIdenticalRequest, process);
  }

  void asJson(SearchRequest request, Map<String, ? extends List<? extends ContextMatch>> response) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    System.out.println("<<<<<< Request >>>>>>");
    System.out.println(gson.toJson(request));
    System.out.println("<<<<<< Response >>>>>>");
    System.out.println(gson.toJson(response));
  }


}