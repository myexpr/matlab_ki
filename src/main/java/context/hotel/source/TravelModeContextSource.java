package context.hotel.source;

import context.hotel.contextof.travelmode.Travel;
import context.hotel.model.SearchRequest;
import context.hotel.model.response.TravelModeMatch;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 19/08/2017.
 */
@Component
public class TravelModeContextSource implements ContextSource {

  @Autowired
  List<Travel> travels;

  public List<TravelModeMatch> deriveContext(SearchRequest request) {

    List<TravelModeMatch> results = travels
        .stream()
        .map(svc -> svc.determineFeasibility(request))
        .collect(Collectors.toList());
    return results;
  }

}
