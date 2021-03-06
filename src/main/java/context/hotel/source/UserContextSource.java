package context.hotel.source;

import static java.util.Arrays.asList;

import context.hotel.contextof.user.FacebookIdentity;
import context.hotel.model.LoggedUser;
import context.hotel.model.SearchRequest;
import context.hotel.model.response.LoggedUserMatch;
import java.util.ArrayList;
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
    List<LoggedUserMatch> loggedUserMatches = new ArrayList<>();
    if (searchRequest.getUser().hasAccessToken()) {
      loggedUser = searchRequest.getResolvedUser();
      loggedUserMatches = asList(
          new LoggedUserMatch("CountriesVisited", loggedUser.countriesVisited()),
          new LoggedUserMatch("ThemesPopular", loggedUser.themesPopular()));
    }
    return loggedUserMatches;
  }


}
