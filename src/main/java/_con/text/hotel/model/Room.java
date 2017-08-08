package _con.text.hotel.model;

public class Room {

  Integer adults;
  Integer children;

  public Room(Integer adults, Integer children) {
    this.adults = adults;
    this.children = children;
  }

  public boolean singleOccupancy() {
    return adults == 1 && children == 0;
  }

  public boolean partyWithChildren() {
    return adults >= 1 && children >= 1;
  }

}
