package context.hotel.constraint.travelmode;

import static com.eclipsesource.json.Json.parse;

import com.eclipsesource.json.JsonObject;
import context.hotel.model.InfeasibleRoute;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by araman on 19/08/2017.
 */
public abstract class RoadRailFeasibilityService implements TravelModeFeasibilityService {

  private static final String API_END_POINT = "https://maps.googleapis.com/maps/api/directions/json?origin={ORIGIN}&destination={DESTINATION}&key={API_KEY}&mode={MODE}";
  private static final Logger LOGGER = LoggerFactory.getLogger(RoadRailFeasibilityService.class);

  public TimeDistance determineTimeDistance(SearchRequest searchRequest) {
    RestTemplate restTemplate = new RestTemplate();
    TimeDistance timeDistance = null;
    String origin = searchRequest.assumedUserOrigin();
    String destination = searchRequest.getResolvedDestination().name();
    String apiTransitMode = forTravelMode().apiTransitMode();

    Map<String, String> myParameters = new HashMap<>();
    myParameters.put("ORIGIN", origin);
    myParameters.put("DESTINATION", destination);
    myParameters.put("API_KEY", "AIzaSyBtDyFsWO-XXXXXXXXXXX-qjjY4nHkk");
    myParameters.put("MODE", apiTransitMode);

    String apiResponse = restTemplate.getForObject(API_END_POINT, String.class, myParameters);
    JsonObject jsonResponse = parse(apiResponse).asObject();
    String responseStatus = jsonResponse.get("status").asString();

    if ("OK".equals(responseStatus)) {
      LOGGER.info("found feasible routes {} {} {}", origin, destination, apiTransitMode);
      JsonObject firstLeg = jsonResponse.get("routes").asArray().get(0).asObject()
          .get("legs").asArray().get(0).asObject();
      Integer distance = firstLeg.get("distance").asObject().get("value").asInt();
      Integer time = firstLeg.get("duration").asObject().get("value").asInt();
      timeDistance = new TimeDistance(distance, time);
    } else {
      LOGGER.info("NO feasible routes {} {} {}", origin, destination, apiTransitMode);
      timeDistance = new InfeasibleRoute();
    }

    return timeDistance;
  }
}
