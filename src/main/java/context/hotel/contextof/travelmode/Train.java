package context.hotel.contextof.travelmode;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.Feasibility.REASONABLE_STRETCH;
import static context.hotel.model.TravelMode.RAIL;

import context.hotel.model.Feasibility;
import context.hotel.model.InfeasibleRoute;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;
import context.hotel.model.response.TravelModeMatch;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 19/08/2017.
 */
@Component
public class Train extends AbstractRoadRail {

  @Override
  public TravelModeMatch determineFeasibility(SearchRequest request) {
    TravelModeMatch result = null;
    Feasibility feasibility = INFEASIBLE;
    TimeDistance timeDistance = super.determineTimeDistance(request);
    int secondsPerHour = 3600;
    if (timeDistance instanceof InfeasibleRoute) {
      feasibility = INFEASIBLE;
    } else if (timeDistance.getTime() < 2 * secondsPerHour) {
      feasibility = DIFFICULT;
    } else if (timeDistance.getTime() >= 2 * secondsPerHour
        && timeDistance.getTime() < 4 * secondsPerHour) {
      feasibility = PREFERRED;
    } else if (timeDistance.getTime() >= 4 * secondsPerHour
        && timeDistance.getTime() < 7 * secondsPerHour) {
      feasibility = REASONABLE_STRETCH;
    } else if (timeDistance.getTime() > 7 * secondsPerHour) {
      feasibility = request.partyWithChildren() ? INFEASIBLE : DIFFICULT;
    }
    return new TravelModeMatch(forTravelMode(), feasibility, timeDistance);
  }

  public TravelMode forTravelMode() {
    return RAIL;
  }
}
