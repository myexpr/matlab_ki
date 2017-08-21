package context.hotel.engine;

import static java.util.Arrays.asList;

import context.hotel.contextof.identity.FacebookIdentity;
import context.hotel.model.LoggedUser;
import context.hotel.model.SearchRequest;
import context.hotel.model.response.LoggedUserMatch;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 20/08/2017.
 */
@Component
public class UserContextSource implements ContextSource {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserContextSource.class);

  @Autowired
  FacebookIdentity facebookIdentity;

  public List<LoggedUserMatch> deriveContext(SearchRequest searchRequest) {
    LoggedUser loggedUser = null;
    try {
      loggedUser = facebookIdentity.retrieveUserDetails();
    } catch (IOException ioe) {
      LOGGER.error("failed to retrieve data from facebook {}", ioe);
    }

    List<LoggedUserMatch> loggedUserMatches = asList(
            new LoggedUserMatch("CountriesVisited", loggedUser.countriesVisited()),
            new LoggedUserMatch("ThemesPopular", loggedUser.themesPopular()));

    return loggedUserMatches;
  }


}
