package context.hotel.engine;

import static java.util.stream.Collectors.groupingBy;

import context.hotel.contextof.travelmode.Travel;
import context.hotel.model.Feasibility;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import context.hotel.model.TravelModeMatch;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 19/08/2017.
 */
@Component
public class TravelModeContextService {

  @Autowired
  List<Travel> travels;

  public Map<Feasibility, List<TravelModeMatch>> getFeasibleTravelModes(SearchRequest request) {

    Map<Feasibility, List<TravelModeMatch>> results = travels
        .stream()
        .map(svc -> {
          TimeDistance timeDistance = svc.determineTimeDistance(request);
          Feasibility feasibility = svc.determineFeasibility(timeDistance, request);
          TravelMode travelMode = svc.forTravelMode();
          return new TravelModeMatch(travelMode, feasibility, timeDistance);
        })
        .collect(groupingBy(TravelModeMatch::getFeasibility));
    return results;
  }

}