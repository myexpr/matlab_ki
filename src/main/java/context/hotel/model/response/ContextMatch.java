package context.hotel.model.response;

import context.hotel.model.Feasibility;

/**
 * Created by araman on 21/08/2017.
 */
public interface ContextMatch {

  public String getContextCategory();
  public String getContextType();
  public Feasibility getFeasibility();
  public Object getData();

}
