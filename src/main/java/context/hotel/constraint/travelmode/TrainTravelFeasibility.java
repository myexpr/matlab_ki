package context.hotel.constraint.travelmode;

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
    Feasibility result = null;
    int sph = 3600;
    if ( timeDistance instanceof InfeasibleRoute) {
      result = Feasibility.INFEASIBLE;
    }
    if ( timeDistance.getTime() < 2*sph ) {
      result = Feasibility.DIFFICULT;
    }
    if ( timeDistance.getTime() >= 2*sph && timeDistance.getTime() < 4*sph) {
      result = Feasibility.PREFERRED;
    }
    if ( timeDistance.getTime() >= 4*sph && timeDistance.getTime() < 7*sph) {
      result = Feasibility.REASONABLE_STRETCH;
    }
    if ( timeDistance.getTime() > 7*sph) {
      result = request.partyWithChildren() ? Feasibility.INFEASIBLE: Feasibility.DIFFICULT;
    }
    return result;
  }

  @Override
  public TravelMode forTravelMode() {
    return TravelMode.RAIL;
  }
}
