package context.hotel.model;

public class Room {

  Integer adults;
  Integer children;

  public Room(Integer adults, Integer children) {
    this.adults = adults;
    this.children = children;
  }
  public Integer numberOfAdults() {
    return adults;
  }

  public Integer numberOfChildren() {
    return children;
  }

  public boolean singleOccupancy() {
    return adults == 1 && children == 0;
  }

  public boolean partyWithChildren() {
    return adults >= 1 && children >= 1;
  }

  @Override
  public String toString() {
    return "Room{" +
        "adults=" + adults +
        ", children=" + children +
        '}';
  }
}
