package _con.text.hotel.model;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.Date;
import java.util.List;
import java.util.function.BinaryOperator;

public class SearchRequest {

  String destination;
  Date fromDate;
  Date toDate;
  List<Room> rooms;
  Integer adults;
  Integer children;

  public SearchRequest(String destination, Date fromDate, Date toDate,
      List<Room> rooms) {
    super();
    this.destination = destination;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.rooms = rooms;
  }

  public Integer numberOfNights() {
    Long between = DAYS.between(fromDate.toInstant(), toDate.toInstant());
    return between.intValue();
  }

  public Integer numberOfRooms() {
    return rooms.size();
  }

  public boolean singleOccupancy() {
    return rooms != null && rooms.size() == 1 && rooms.iterator().next().singleOccupancy();
  }

  public boolean partyWithChildren() {
    return rooms != null && rooms.size() == 1 && rooms.iterator().next().partyWithChildren();
  }

  public Integer numberOfAdults() {
    return rooms.stream().mapToInt(Room::numberOfAdults).sum();
  }
}
