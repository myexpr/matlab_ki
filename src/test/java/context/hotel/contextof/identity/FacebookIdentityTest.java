package context.hotel.contextof.identity;

import java.io.IOException;
import org.junit.Test;

/**
 * Created by araman on 20/08/2017.
 */
public class FacebookIdentityTest {

  @Test
  public void testCall() throws IOException {
    new FacebookIdentity().retrieveUserDetails();
  }


}