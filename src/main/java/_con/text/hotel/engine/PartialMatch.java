package _con.text.hotel.engine;


import java.util.Objects;

public class PartialMatch {

  Integer probability;
  SearchType searchType;

  public PartialMatch(SearchType searchType, Integer probability) {
    this.probability = probability;
    this.searchType = searchType;
  }

  public Integer getProbability() {
    return probability;
  }

  public SearchType getSearchType() {
    return searchType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartialMatch that = (PartialMatch) o;
    return Objects.equals(probability, that.probability) &&
        searchType == that.searchType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(probability, searchType);
  }

  @Override
  public String toString() {
    return "PartialMatch{" +
        "searchType=" + searchType +
        ", probability=" + probability +
        '}';
  }
}
