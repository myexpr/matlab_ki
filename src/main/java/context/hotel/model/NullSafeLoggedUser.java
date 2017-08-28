package context.hotel.model;

import java.util.Collections;

/**
 * Created by araman on 28/08/2017.
 */
public class NullSafeLoggedUser extends LoggedUser {

  public NullSafeLoggedUser() {
    super("", "", "", Collections.emptyList());
  }

  @Override
  public String toString() {
    return "NullSafeLoggedUser{}";
  }
}
