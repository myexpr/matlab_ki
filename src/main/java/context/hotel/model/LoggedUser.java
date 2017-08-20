package context.hotel.model;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by araman on 20/08/2017.
 */
public class LoggedUser {

  private final String name;
  private final String email;
  private final String location;
  private final List<VisitedPlace> visitedPlaces;

  public LoggedUser(String name, String email, String location,
      List<VisitedPlace> visitedPlaces) {
    this.name = name;
    this.email = email;
    this.location = location;
    this.visitedPlaces = visitedPlaces;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getLocation() {
    return location;
  }

  public List<VisitedPlace> getVisitedPlaces() {
    return visitedPlaces;
  }

  public Map<String, Long> countriesVisited() {
    Map<String, Long> statistics = visitedPlaces
        .stream()
        .collect(groupingBy(VisitedPlace::getCountry, counting()));
    return statistics;
  }

  public Map<String, Long> themesPopular() {
    Map<String, Long> themeStatistics = visitedPlaces
        .stream()
        .flatMap((VisitedPlace t) -> t.getThemes().stream())
        .collect(Collectors.groupingBy(String::toString, counting()));
    return themeStatistics;
  }

  @Override
  public String toString() {
    return "LoggedUser{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", location='" + location + '\'' +
        ", visitedPlaces=" + visitedPlaces +
        '}';
  }
}
