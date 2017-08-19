package context.hotel.constraint.occupancy;

import context.hotel.model.SearchRequest;

/**
 * Created by araman on 09/08/2017.
 */
public interface OccupancyConstraint {

  public boolean evaluate(SearchRequest request);

}
