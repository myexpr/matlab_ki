package context.hotel.constraint.business;

import context.hotel.model.SearchRequest;

/**
 * Created by araman on 09/08/2017.
 */
public interface BusinessConstraints {

  public boolean evaluate(SearchRequest request);

}
