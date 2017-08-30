package context.hotel.contextof.user;

import static java.time.LocalDate.now;

import context.hotel.controller.SearchController;
import context.hotel.model.Room;
import context.hotel.model.SearchRequest;
import context.hotel.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by araman on 29/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookIdentityTest {

  @Autowired
  SearchController searchController;

  @Test
  public void foo() {
    User shiva = new User(
        "EAACEdEose0cBAIjm09cZC3ZAIlBUCFTn5WoJ0qPGp5m7oG5lZBuK8WyZCZAWDOYZBYuCIVbxubNzbhUWkHokZCZA3J53mcH4hXs8nsISVoSXq8H2Aup4Eb4iOZAR6SJ5jBtynKzZA5disPmMIUdxQ15uadu7n6mI4oUBwYtk81vic7z6F3lC2dnrYrrFO5h3a62JTFnUQvgx8mL5RRfq1BqcNhS8YqDfgHwCySPNG5SAmkFwZDZD");
    SearchRequest searchRequest = new SearchRequest("605", now(), now().plusDays(3), new Room(1, 0), shiva);

    System.out.println(searchController.searchForDestination(searchRequest));
  }


}
