package context.hotel.contextof.travelmode;

import context.hotel.model.Feasibility;
import context.hotel.model.SearchRequest;
import context.hotel.model.TimeDistance;
import context.hotel.model.TravelMode;

/**
 * Created by araman on 19/08/2017.
 */
public interface Travel {

  TimeDistance determineTimeDistance(SearchRequest request);

  Feasibility determineFeasibility(TimeDistance timeDistance, SearchRequest request);

  TravelMode forTravelMode();
}
