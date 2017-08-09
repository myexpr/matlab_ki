package _con.text.hotel.model;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class SimpleSearchRequest implements SearchRequest {

  String destination;
  Date fromDate;
  Date toDate;
  List<Room> rooms;
  Integer adults;
  Integer children;

  public SimpleSearchRequest(String destination, Date fromDate, Date toDate,
      List<Room> rooms) {
    super();
    this.destination = destination;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.rooms = rooms;
  }

  public long numberOfNights() {
    return DAYS.between(fromDate.toInstant(), toDate.toInstant());
  }

  public long numberOfRooms() {
    return rooms.size();
  }

  public boolean singleOccupancy() {
    return rooms != null && rooms.size() == 1 && rooms.iterator().next().singleOccupancy();
  }

  public boolean partyWithChildren() {
    return rooms != null && rooms.size() == 1 && rooms.iterator().next().partyWithChildren();
  }

}
