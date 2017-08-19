package context.hotel.model;


import java.util.Objects;

public class PartialMatch {

  Integer matchPercentage;
  SearchType searchType;

  public PartialMatch(SearchType searchType, Integer matchPercentage) {
    this.matchPercentage = matchPercentage;
    this.searchType = searchType;
  }

  public Integer getMatchPercentage() {
    return matchPercentage;
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
    return Objects.equals(matchPercentage, that.matchPercentage) &&
        searchType == that.searchType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(matchPercentage, searchType);
  }

  @Override
  public String toString() {
    return "PartialMatch{" +
        "searchType=" + searchType +
        ", matchPercentage=" + matchPercentage +
        '}';
  }
}
