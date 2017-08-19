package context.hotel.contextof.travelmode;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.Feasibility.REASONABLE_STRETCH;

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
public class Road extends AbstractRoadRail {

  @Override
  public Feasibility determineFeasibility(TimeDistance timeDistance, SearchRequest request) {
    Feasibility result = INFEASIBLE;
    int km = 1000;
    if (timeDistance instanceof InfeasibleRoute) {
      return INFEASIBLE;
    }
    if (timeDistance.getDistance() <= 500 * km) {
      return PREFERRED;
    }
    if (timeDistance.getDistance() > 500 * km && timeDistance.getDistance() < 800 * km) {
      return request.partyWithChildren() ? DIFFICULT : REASONABLE_STRETCH;
    }
    if (timeDistance.getDistance() >= 800 * km) {
      return request.partyWithChildren() ? INFEASIBLE : DIFFICULT;
    }
    return result;
  }

  @Override
  public TravelMode forTravelMode() {
    return TravelMode.ROAD;
  }

}
