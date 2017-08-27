package context.hotel.model;

public class Room {

  Integer adults;
  Integer children;

  public Room() {
  }

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

  public void setAdults(Integer adults) {
    this.adults = adults;
  }

  public void setChildren(Integer children) {
    this.children = children;
  }
  @Override
  public String toString() {
    return "Room{" +
        "adults=" + adults +
        ", children=" + children +
        '}';
  }
}
