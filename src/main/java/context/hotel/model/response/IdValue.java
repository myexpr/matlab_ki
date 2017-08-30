package context.hotel.model.response;

/**
 * Created by araman on 30/08/2017.
 */
public class IdValue implements  Comparable<IdValue> {

  private final String id;
  private final String value;

  public IdValue(String id, String value) {
    this.id = id;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public String getValue() {
    return value;
  }

  @Override
  public int compareTo(IdValue o) {
    return this.value.compareTo(o.value);
  }
}
