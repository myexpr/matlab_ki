package context.hotel.engine;

import static context.hotel.model.TravelMode.RAIL;
import static context.hotel.model.TravelMode.ROAD;

import context.hotel.constraint.business.RoadTravelFeasibility;
import context.hotel.model.Destination;
import context.hotel.model.Feasibility;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 19/08/2017.
 */
@Component
public class FeasibleTravelModeService {

  @Autowired
  TimeDistanceService timeDistanceService;

  @Autowired
  RoadTravelFeasibility roadTravelFeasibility;

  public Map<TravelMode, Feasibility> getFeasibleTravelModes(SearchRequest request, Destination destination) {
    TimeDistance viaRoad = timeDistanceService.timeAndDistanceTo(request.assumedUserOrigin(), destination.name(), ROAD);
    TimeDistance viaTrain = timeDistanceService.timeAndDistanceTo(request.assumedUserOrigin(), destination.name(), RAIL);
    Map<TravelMode, Feasibility> result = new HashMap<>();
    result.put(ROAD, roadTravelFeasibility.roadFeasibility(viaRoad, request));
    return result;
  }

}
