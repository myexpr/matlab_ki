package context.hotel.engine;

import context.hotel.contextof.travelmode.Travel;
import context.hotel.model.Feasibility;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
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
        .map(svc -> {
          TimeDistance timeDistance = svc.determineTimeDistance(request);
          Feasibility feasibility = svc.determineFeasibility(timeDistance, request);
          TravelMode travelMode = svc.forTravelMode();
          return new TravelModeMatch(travelMode, feasibility, timeDistance);
        })
        .collect(Collectors.toList());
    return results;
  }

}
