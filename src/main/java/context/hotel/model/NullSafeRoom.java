package context.hotel.model;

public class NullSafeRoom extends Room {

  public NullSafeRoom() {
    this.adults = 0;
    this.children = 0;
  }

  @Override
  public void setAdults(Integer adults) {
    throw new UnsupportedOperationException("null safe class. doesnt support set");
  }

  @Override
  public void setChildren(Integer children) {
    throw new UnsupportedOperationException("null safe class. doesnt support set");
  }

  @Override
  public String toString() {
    return "NullSafeRoom{" +
        "adults=" + adults +
        ", children=" + children +
        '}';
  }
}
