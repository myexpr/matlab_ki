package context.hotel.controller;


import static java.util.stream.Collectors.toList;

import context.hotel.contextof.user.FacebookIdentity;
import context.hotel.engine.ContextEngine;
import context.hotel.model.Destination;
import context.hotel.model.LoggedUser;
import context.hotel.model.SearchRequest;
import context.hotel.model.response.ContextMatch;
import context.hotel.model.response.IdValue;
import context.hotel.repository.DestinationRepository;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

  @Autowired
  DestinationRepository destinationRepository;
  @Autowired
  FacebookIdentity facebookIdentity;
  @Autowired
  ContextEngine engine;

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

  @RequestMapping(path = "/search", method = RequestMethod.POST)
  @CrossOrigin
  public Map<String, ? extends List<? extends ContextMatch>> searchForDestination(@RequestBody
      SearchRequest searchRequest) {

    Destination resolvedDestination = destinationRepository
        .findOne(searchRequest.getDestinationId());
    searchRequest.setResolvedDestination(resolvedDestination);

    if (searchRequest.getUser().hasAccessToken()) {
      LoggedUser loggedUser = facebookIdentity
          .retrieveUserDetails(searchRequest.getUser().getAccessToken());
      searchRequest.setResolvedUser(loggedUser);
    }
    LOGGER.debug("search request {}", searchRequest);

    return engine.process(searchRequest);
  }

  @RequestMapping(path = "/type", method = RequestMethod.GET)
  @CrossOrigin
  public List<IdValue> findCities(@RequestParam String q) {
    List<Destination> destinations = destinationRepository.findTop15ByCityStartingWithIgnoreCaseOrderByCityAsc(q);

    List<IdValue> typeAheadEnteries = destinations
        .stream()
        .map(d -> new IdValue(d.getDestinationId(), d.name()))
        .sorted()
        .collect(toList());

    return typeAheadEnteries;
  }

}
