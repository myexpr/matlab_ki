package context.hotel.contextof.identity;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by araman on 20/08/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookIdentityTest {

  @Autowired
  FacebookIdentity facebookIdentity;

  @Test
  public void testCall() throws IOException {
    facebookIdentity.retrieveUserDetails();
  }


}