package context.hotel.model;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DestinationTest {

  @Test
  public void testSomeDistances() {
    Double latA, latB, longA, longB;
    latA = 52.5597;
    longA = 13.2877;
    latB = 51.4706;
    longB = -0.461941;

    Destination d = new Destination("57565", "SOMECITY", "AREGION", "IN", latA, longA);

    assertEquals(valueOf(947), d.distanceFrom(latB, longB));
  }

  @Test
  public void shouldOnlyConcatenateCityCountryWhenCountryNotUS() {
    Destination destination = new Destination("FOO", "Bath", "Bucks", "GB", 0.0d, 0.0d);
    assertEquals("Bath, GB", destination.name());
  }

  @Test
  public void shouldConcatenateCityRegionCountryWhenCountryNotUS() {
    Destination destination = new Destination("FOO", "Cambridge", "MA", "US", 0.0d, 0.0d);
    assertEquals("Cambridge, MA, US", destination.name());
  }


}
