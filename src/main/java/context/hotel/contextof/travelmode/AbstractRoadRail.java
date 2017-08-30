package context.hotel.contextof.travelmode;

import static com.eclipsesource.json.Json.parse;

import com.eclipsesource.json.JsonObject;
import context.hotel.model.InfeasibleRoute;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by araman on 19/08/2017.
 */
public abstract class AbstractRoadRail implements Travel {

  private static final String API_END_POINT = "https://maps.googleapis.com/maps/api/directions/json?origin={ORIGIN}&destination={DESTINATION}&key={API_KEY}&mode={MODE}";
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRoadRail.class);

  public TimeDistance determineTimeDistance(SearchRequest searchRequest) {
    RestTemplate restTemplate = new RestTemplate();
    TimeDistance timeDistance = null;
    String destination = searchRequest.getResolvedDestination().name();
    String apiTransitMode = forTravelMode().apiTransitMode();
    String origin = null;

    if (searchRequest.assumedUserOrigin() == null) {
      return new InfeasibleRoute();
    }
    origin = searchRequest.assumedUserOrigin();
    LOGGER.debug("finding feasible routes origin:{} dest:{} apimode:{} ", origin,
        destination, apiTransitMode);
    Map<String, String> myParameters = new HashMap<>();
    myParameters.put("ORIGIN", origin);
    myParameters.put("DESTINATION", destination);
    myParameters.put("API_KEY", ""); //works without one.
    myParameters.put("MODE", apiTransitMode);

    String apiResponse = restTemplate.getForObject(API_END_POINT, String.class, myParameters);
    JsonObject jsonResponse = parse(apiResponse).asObject();
    String responseStatus = jsonResponse.get("status").asString();

    if ("OK".equals(responseStatus)) {
      JsonObject firstLeg = jsonResponse.get("routes").asArray().get(0).asObject()
          .get("legs").asArray().get(0).asObject();
      Integer distance = firstLeg.get("distance").asObject().get("value").asInt();
      Integer time = firstLeg.get("duration").asObject().get("value").asInt();
      LOGGER.info("found feasible routes origin:{} dest:{} apimode:{} time:{} distance:{}", origin,
          destination, apiTransitMode, time, distance);
      timeDistance = new TimeDistance(distance, time);
    } else {
      LOGGER.info("NO feasible routes {} {} {}", origin, destination, apiTransitMode);
      timeDistance = new InfeasibleRoute();
    }

    return timeDistance;
  }

}
