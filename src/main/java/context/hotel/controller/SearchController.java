package context.hotel.controller;


import context.hotel.engine.SearchTypeContextService;
import context.hotel.model.Destination;
import context.hotel.model.PartialMatch;
import context.hotel.model.SearchRequest;
import context.hotel.repository.DestinationRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

  @Autowired
  DestinationRepository destinationRepository;
  @Autowired
  SearchTypeContextService searchTypeContextService;

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

  @RequestMapping(path = "/search", method = RequestMethod.POST)
  public List<PartialMatch> searchForDestination(SearchRequest searchRequest) {
    LOGGER.debug("search request {}", searchRequest);
    Destination resolvedDestination = destinationRepository
        .findOne(searchRequest.getDestinationId());
    searchRequest.setResolvedDestination(resolvedDestination);

    List<PartialMatch> partialMatches = searchTypeContextService.deriveContext(searchRequest);

    return partialMatches;
  }


}
