package context.hotel.engine;

import context.hotel.model.SearchRequest;
import context.hotel.model.response.ContextMatch;
import java.util.List;

/**
 * Created by araman on 20/08/2017.
 */
public interface ContextSource {

  public List<? extends ContextMatch> deriveContext(SearchRequest searchRequest);

}
