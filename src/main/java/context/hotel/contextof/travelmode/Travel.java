package context.hotel.contextof.travelmode;

import context.hotel.model.SearchRequest;
import context.hotel.model.TravelMode;
import context.hotel.model.response.TravelModeMatch;

/**
 * Created by araman on 19/08/2017.
 */
public interface Travel {

  TravelModeMatch determineFeasibility(SearchRequest request);

  TravelMode forTravelMode();
}
