package context.hotel.contextof.travelmode;

import static com.eclipsesource.json.Json.parse;
import static context.hotel.model.TravelMode.AIR;

import com.eclipsesource.json.JsonObject;
import context.hotel.model.Destination;
import context.hotel.model.InfeasibleRoute;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import context.hotel.model.response.TravelModeMatch;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by araman on 19/08/2017.
 */
public class Air {

  private static final String GOOGLE_PLACES_API =
      "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={LATITUDE},{LONGITUDE}&radius=100000&type=airport&key={API_KEY}";

  private static final Logger LOGGER = LoggerFactory.getLogger(Air.class);

  private String API_KEY;

  public TimeDistance determineTimeDistance(SearchRequest searchRequest) {
    RestTemplate restTemplate = new RestTemplate();
    TimeDistance timeDistance = null;
    Destination destination = searchRequest.getResolvedDestination();

    Map<String, String> myParameters = new HashMap<>();
    myParameters.put("LATITUDE", destination.getLatitude().toString());
    myParameters.put("LONGITUDE", destination.getLongitude().toString());
    myParameters.put("API_KEY", API_KEY);

    String apiResponse = restTemplate.getForObject(GOOGLE_PLACES_API, String.class, myParameters);
    JsonObject jsonResponse = parse(apiResponse).asObject();
    String responseStatus = jsonResponse.get("status").asString();

    if ("OK".equals(responseStatus)) {
      JsonObject firstResult = jsonResponse.get("results").asArray().get(0).asObject();
      JsonObject location = firstResult.get("geometry").asObject().get("location").asObject();
      String airportName = firstResult.get("name").asString();
      LOGGER.info("airport {} located within 100 km of destination {}", airportName, destination);
      return new TimeDistance(100 * 1000, 3 * 3600);
    } else {
      return new InfeasibleRoute();
    }
  }

  public TravelModeMatch determineFeasibility(SearchRequest request) {
    return null;
  }

  public TravelMode forTravelMode() {
    return AIR;
  }
}
