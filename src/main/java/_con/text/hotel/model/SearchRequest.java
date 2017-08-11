package _con.text.hotel.model;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.asList;

import java.time.LocalDate;
import java.util.List;

public class SearchRequest {

  String destination;
  LocalDate fromDate;
  LocalDate toDate;
  List<Room> rooms;
  Integer adults;
  Integer children;

  public SearchRequest(String destination, LocalDate fromDate, LocalDate toDate,
      List<Room> rooms) {
    super();
    this.destination = destination;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.rooms = rooms;
  }

  public SearchRequest(String destination, LocalDate fromDate, LocalDate toDate,
      Room room) {
    this(destination, fromDate, toDate, asList(room));
  }

  public Integer numberOfNights() {
    Long between = fromDate.until(toDate, DAYS);
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

  public Integer numberOfChildren() {
    return rooms.stream().mapToInt(Room::numberOfChildren).sum();
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  @Override
  public String toString() {
    return "SearchRequest{" +
        "destination='" + destination + '\'' +
        ", fromDate=" + fromDate +
        ", toDate=" + toDate +
        ", rooms=" + rooms +
        ", adults=" + adults +
        ", children=" + children +
        '}';
  }


}
