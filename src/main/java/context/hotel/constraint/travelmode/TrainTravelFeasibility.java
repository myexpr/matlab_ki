package context.hotel.constraint.travelmode;

import static context.hotel.model.Feasibility.*;

import context.hotel.model.Feasibility;
import context.hotel.model.InfeasibleRoute;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 19/08/2017.
 */
@Component
public class TrainTravelFeasibility extends RoadRailFeasibilityService {

  @Override
  public Feasibility determineFeasibility(TimeDistance timeDistance, SearchRequest request) {
    Feasibility result = INFEASIBLE;
    int secondsPerHour = 3600;
    if (timeDistance instanceof InfeasibleRoute) {
      return INFEASIBLE;
    }
    if (timeDistance.getTime() < 2 * secondsPerHour) {
      return DIFFICULT;
    }
    if (timeDistance.getTime() >= 2 * secondsPerHour && timeDistance.getTime() < 4 * secondsPerHour) {
      return PREFERRED;
    }
    if (timeDistance.getTime() >= 4 * secondsPerHour && timeDistance.getTime() < 7 * secondsPerHour) {
      return REASONABLE_STRETCH;
    }
    if (timeDistance.getTime() > 7 * secondsPerHour) {
      return request.partyWithChildren() ? INFEASIBLE : DIFFICULT;
    }
    return result;
  }

  @Override
  public TravelMode forTravelMode() {
    return TravelMode.RAIL;
  }
}
