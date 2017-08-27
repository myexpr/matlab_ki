package context.hotel.model;

import static java.lang.String.format;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.asList;

import java.time.LocalDate;
import java.util.List;

public class SearchRequest {

  String destinationId;
  LocalDate fromDate;
  LocalDate toDate;
  List<Room> rooms;
  User user;
  Destination resolvedDestination;

  public SearchRequest() {
  }

  public SearchRequest(String destinationId, LocalDate fromDate, LocalDate toDate,
      List<Room> rooms, User user) {
    super();
    this.destinationId = destinationId;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.rooms = rooms;
    this.user = user;
  }

  public SearchRequest(String destinationId, LocalDate fromDate, LocalDate toDate,
      Room room, User user) {
    this(destinationId, fromDate, toDate, asList(room), user);
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

  public String assumedUserOrigin() {
    GeoCoordinate coordinate = user.getGeoCoordinate();
    String result = null;
    if (coordinate != null && coordinate.isValid()) {
      result = format("%s,%s", coordinate.getLatitude(),
          coordinate.getLongitude());
    }
    return result;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public String getDestinationId() {
    return destinationId;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public User getUser() {
    return user;
  }

  public Destination getResolvedDestination() {
    return resolvedDestination;
  }

  public void setResolvedDestination(Destination resolvedDestination) {
    this.resolvedDestination = resolvedDestination;
  }

  public void setDestinationId(String destinationId) {
    this.destinationId = destinationId;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "SearchRequest{" +
        "destinationId='" + destinationId + '\'' +
        ", fromDate=" + fromDate +
        ", toDate=" + toDate +
        ", rooms=" + rooms +
        ", user=" + user +
        ", resolvedDestination=" + resolvedDestination +
        '}';
  }

}
