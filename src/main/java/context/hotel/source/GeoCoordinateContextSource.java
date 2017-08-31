package context.hotel.source;

import static com.eclipsesource.json.Json.parse;
import static java.util.Arrays.asList;

import com.eclipsesource.json.JsonObject;
import context.hotel.model.Destination;
import context.hotel.model.GeoCoordinate;
import context.hotel.model.NullSafeGeoCoordinate;
import context.hotel.model.SearchRequest;
import context.hotel.model.response.ContextMatch;
import context.hotel.model.response.GeoCoordinateContextMatch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeoCoordinateContextSource implements ContextSource {

  private static final String API_END_POINT = "https://maps.googleapis.com/maps/api/geocode/json?address={ORIGIN}&key={API_KEY}";
  private static final Logger LOGGER = LoggerFactory.getLogger(GeoCoordinateContextSource.class);

  @Value("${secret.google}")
  private String apiKey;

  @Override
  public List<? extends ContextMatch> deriveContext(SearchRequest searchRequest) {
    LOGGER.debug("processing the geocoordinate context source");

    Destination resolvedDestination = searchRequest.getResolvedDestination();
    GeoCoordinateContextMatch destination = new GeoCoordinateContextMatch("Destination",
        resolvedDestination.name(), resolvedDestination.getGeoCoordinate());

    String assumedUserOrigin = searchRequest.assumedUserOrigin();
    GeoCoordinate originGeoCoord = resolveOrigin(assumedUserOrigin);
    GeoCoordinateContextMatch origin = new GeoCoordinateContextMatch("Origin", assumedUserOrigin,
        originGeoCoord);

    List<GeoCoordinateContextMatch> result = asList(origin, destination);
    return result;
  }

  GeoCoordinate resolveOrigin(String assumedUserOrigin) {
    GeoCoordinate resolvedGeoCoordinate = null;
    Double latitude = null;
    Double longitude = null;
    RestTemplate restTemplate = new RestTemplate();

    try {
      LOGGER.debug("finding geocoordinate for origin:{}", assumedUserOrigin);

      Map<String, String> myParameters = new HashMap<>();
      myParameters.put("ORIGIN", assumedUserOrigin);
      myParameters.put("API_KEY", apiKey);

      String apiResponse = restTemplate.getForObject(API_END_POINT, String.class, myParameters);
      JsonObject jsonResponse = parse(apiResponse).asObject();
      JsonObject resolvedLocationJson = jsonResponse.get("results").asArray().get(0).asObject()
          .get("geometry")
          .asObject().get("location").asObject();

      latitude = resolvedLocationJson.getDouble("lat", 0d);
      longitude = resolvedLocationJson.getDouble("lng", 0d);
      resolvedGeoCoordinate = new GeoCoordinate(latitude, longitude);
      LOGGER.info("resolved origin:{}. as geocoord {}", assumedUserOrigin, resolvedGeoCoordinate);
    } catch (Exception e) {
      LOGGER.info("couldnt resolve origin:{}. failed with exception ", assumedUserOrigin, e);
      resolvedGeoCoordinate = new NullSafeGeoCoordinate();
    }
    return resolvedGeoCoordinate;
  }


}
