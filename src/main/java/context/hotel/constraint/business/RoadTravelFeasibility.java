package context.hotel.constraint.business;

import static context.hotel.model.Feasibility.DIFFICULT;
import static context.hotel.model.Feasibility.INFEASIBLE;
import static context.hotel.model.Feasibility.PREFERRED;
import static context.hotel.model.Feasibility.REASONABLE_STRETCH;

import context.hotel.model.Feasibility;
import context.hotel.model.InfeasibleRoute;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 19/08/2017.
 */
@Component
public class RoadTravelFeasibility {

  public Feasibility roadFeasibility(TimeDistance timeDistance, SearchRequest request) {
    Feasibility result = null;
    if (timeDistance instanceof InfeasibleRoute) {
      result = INFEASIBLE;
    }
    if (timeDistance.getDistance() <= 500) {
      result = PREFERRED;
    }
    if (timeDistance.getDistance() > 500 && timeDistance.getDistance() < 800) {
      result = request.partyWithChildren() ? DIFFICULT : REASONABLE_STRETCH;
    }
    if (timeDistance.getDistance() >= 800) {
      result = request.partyWithChildren() ? INFEASIBLE : DIFFICULT;
    }
    return result;
  }

}
