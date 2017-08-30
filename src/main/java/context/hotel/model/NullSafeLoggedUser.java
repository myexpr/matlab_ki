package context.hotel.model;

import static java.util.Collections.emptyList;

/**
 * Created by araman on 28/08/2017.
 */
public class NullSafeLoggedUser extends LoggedUser {

  public NullSafeLoggedUser() {
    super("", "", "", emptyList());
  }

  @Override
  public String toString() {
    return "NullSafeLoggedUser{}";
  }
}
