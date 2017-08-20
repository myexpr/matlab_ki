package context.hotel.model;

import java.util.List;

/**
 * Created by araman on 20/08/2017.
 */
public class VisitedPlace {

  private final String name;
  private final String city;
  private final String country;
  private final List<String> themes;

  public VisitedPlace(String name, String city, String country, List<String> themes) {
    this.name = name;
    this.city = city;
    this.country = country;
    this.themes = themes;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public List<String> getThemes() {
    return themes;
  }

  @Override
  public String toString() {
    return "VisitedPlace{" +
        "name='" + name + '\'' +
        ", city='" + city + '\'' +
        ", country='" + country + '\'' +
        ", themes=" + themes +
        '}';
  }
}
