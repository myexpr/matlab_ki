package context.hotel.repository;

import context.hotel.model.Destination;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface DestinationRepository extends CrudRepository<Destination, Integer> {

  List<Destination> findByCity(String city);

}