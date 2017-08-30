package context.hotel.contextof.travelmode;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.Feasibility.REASONABLE_STRETCH;
import static context.hotel.model.TravelMode.*;

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
public class Road extends AbstractRoadRail {

  @Override
  public TravelModeMatch determineFeasibility(SearchRequest request) {
    TravelMode travelMode = null;
    TimeDistance timeDistance = super.determineTimeDistance(request);
    Feasibility feasibility = INFEASIBLE;
    int km = 1000;
    if (timeDistance instanceof InfeasibleRoute) {
      feasibility = INFEASIBLE;
    } else if (timeDistance.getDistance() <= 500 * km) {
      feasibility = PREFERRED;
    } else if (timeDistance.getDistance() > 500 * km && timeDistance.getDistance() < 800 * km) {
      feasibility =
          request.partyWithChildren() ? DIFFICULT : REASONABLE_STRETCH;
    } else if (timeDistance.getDistance() >= 800 * km) {
      feasibility = request.partyWithChildren() ? INFEASIBLE : DIFFICULT;
    }
    return new TravelModeMatch(forTravelMode(), feasibility, timeDistance);
  }

  public TravelMode forTravelMode() {
    return ROAD;
  }

}
