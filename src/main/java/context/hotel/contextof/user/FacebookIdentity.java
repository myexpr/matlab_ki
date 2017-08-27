package context.hotel.contextof.user;

import static java.util.Optional.ofNullable;
import static org.apache.http.client.fluent.Form.form;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import context.hotel.model.LoggedUser;
import context.hotel.model.VisitedPlace;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 20/08/2017.
 */
@Component
public class FacebookIdentity {

  @Value("${secret.facebook}")
  private String ACCESS_TOKEN;

  private static final String FB_URL = "https://graph.facebook.com/v2.10/me"; //?fields=%s&access_token=%s";
  private static final String FIELDS;
  private static final Logger LOGGER = LoggerFactory.getLogger(FacebookIdentity.class);

  static {
    FIELDS = "name,email,location,tagged_places{place{name,location,place_topics}}";
  }

  //todo: has to be removed when we have user access tokens
  public LoggedUser retrieveUserDetails() throws IOException {
    return this.retrieveUserDetails(ACCESS_TOKEN);
  }

  public LoggedUser retrieveUserDetails(String accessToken) throws IOException {
    String facebookResponse = Request.Post(FB_URL)
        .bodyForm(form()
            .add("fields", FIELDS).add("access_token", accessToken).add("limit", "50").build())
        .execute().returnContent().asString();

    JsonObject jsonResponse = Json.parse(facebookResponse).asObject();

    String userName = jsonResponse.getString("name", "");
    String userEmail = jsonResponse.getString("email", "");
    String userLocation = safeGetFieldFrom(jsonResponse.get("location"), "name");

    List<VisitedPlace> visitedPlaces = new ArrayList<>();
    JsonArray taggedPlacesResponse = safeGetArrayFrom(jsonResponse.get("tagged_places"), "data");
    taggedPlacesResponse.iterator().forEachRemaining(value -> {
      JsonObject place = value.asObject().get("place").asObject();
      JsonObject location = place.get("location").asObject();
      JsonArray placeTopics = safeGetArrayFrom(place.get("place_topics"), "data");

      String placeName = place.asObject().getString("name", "UNKNOWN");
      String placeCity = location.getString("city", "UNKNOWN");
      String placeCountry = location.getString("country", "UNKNOWN");

      List<String> placeThemes = new ArrayList<>();

      placeTopics.iterator().forEachRemaining(topicData -> {
        String placeTheme = topicData.asObject().getString("name", "");
        placeThemes.add(placeTheme);
      });

      visitedPlaces.add(new VisitedPlace(placeName, placeCity, placeCountry, placeThemes));
    });

    LoggedUser user = new LoggedUser(userName, userEmail, userLocation, visitedPlaces);
    LOGGER.debug("determined user details {}", new ObjectMapper().writeValueAsString(user));
    LOGGER.debug("user countries {}", user.countriesVisited());
    LOGGER.debug("user themes {}", user.themesPopular());
    return user;
  }

  JsonArray safeGetArrayFrom(JsonValue object, String field) {
    return ofNullable(object).map(t -> {
      return t.asObject().get(field).asArray();
    }).orElse(new JsonArray());
  }

  String safeGetFieldFrom(JsonValue object, String fieldName) {
    return ofNullable(object).map(t -> {
      return t.asObject().getString("name", "");
    }).orElse("");
  }


}
