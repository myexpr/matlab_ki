package _con.text.hotel.model;

public enum SearchType {

  BUSINESS(1, 1, 1, 3);

  private final int numberOfRooms;
  private final int numberOfAdults;
  private final int minNightStay;
  private final int maxNightStay;

  SearchType(int numberOfRooms, int numberOfAdults, int minNightStay, int maxNightStay) {
    this.numberOfRooms = numberOfRooms;
    this.numberOfAdults = numberOfAdults;
    this.minNightStay = minNightStay;
    this.maxNightStay = maxNightStay;
  }
}
