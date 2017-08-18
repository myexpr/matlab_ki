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
    System.out.println("in command runner");
    System.out.println(repository);
    return (args) -> {

      for (Destination d : repository.findByCity("Pittsburgh")) {
        System.out.println(d.toString());
      }
    };
  }

}
