package context.hotel;

import context.hotel.model.Destination;
import context.hotel.repository.DestinationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelApplication {

  public static void main(String[] args) {
    SpringApplication.run(HotelApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(DestinationRepository repository) {
    return (args) -> {
      repository.save(new Destination(1, "foo", "", "GB", -1.009, -56.98));
      repository.save(new Destination(2, "foo2", "", "GB", -1.009, -56.98));

      for (Destination d : repository.findAll()) {
        System.out.println(d.toString());
      }

    };
  }

}
