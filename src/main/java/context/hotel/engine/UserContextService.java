package context.hotel.engine;

import context.hotel.contextof.identity.FacebookIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 20/08/2017.
 */
@Component
public class UserContextService {

  @Autowired
  FacebookIdentity facebookIdentity;



}
