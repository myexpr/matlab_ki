package context.hotel.model.response;

import static context.hotel.model.Feasibility.UNKNOWN;

import context.hotel.model.Feasibility;
import java.util.Map;

/**
 * Created by araman on 21/08/2017.
 */
public class LoggedUserMatch implements ContextMatch {

  private final String contextType;
  private final Map<String, Long> data;

  public LoggedUserMatch(String type, Map<String, Long> data) {
    this.contextType = type;
    this.data = data;
  }

  @Override
  public String getContextCategory() {
    return "UserContext";
  }

  @Override
  public String getContextType() {
    return this.contextType;
  }

  @Override
  public Feasibility getFeasibility() {
    return UNKNOWN;
  }

  @Override
  public Object getData() {
    return this.data;
  }

  @Override
  public String toString() {
    return "LoggedUserMatch{" +
        "contextType='" + contextType + '\'' +
        ", data=" + data +
        '}';
  }
}
