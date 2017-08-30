package context.hotel.repository;

import context.hotel.model.Airport;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by araman on 30/08/2017.
 */
public interface AirportRepository extends CrudRepository<Airport, String> {

  @Query("select a from Airport a where a.type = 'large_airport' and a.scheduledService = 'yes'")
  public abstract List<Airport> findAllActiveLargeAirports();
}
