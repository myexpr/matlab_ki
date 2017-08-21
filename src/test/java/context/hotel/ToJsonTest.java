package context.hotel;

import com.google.gson.Gson;
import context.hotel.model.User;
import org.junit.Test;

/**
 * Created by araman on 21/08/2017.
 */
public class ToJsonTest {

  @Test
  public void foo() {
    User accessToken = new User("accessToken"); //, new GeoCoordinate(0943d, 9878d));
    System.out.println(new Gson().toJson(accessToken));
  }

}
