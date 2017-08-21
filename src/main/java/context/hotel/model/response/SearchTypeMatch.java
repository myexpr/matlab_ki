package context.hotel.model.response;


import static context.hotel.model.Feasibility.*;
import static context.hotel.model.Feasibility.INFEASIBLE;

import context.hotel.model.Feasibility;
import context.hotel.model.SearchType;
import java.util.Objects;

public class SearchTypeMatch implements ContextMatch {

  Integer matchPercentage;
  SearchType searchType;

  public SearchTypeMatch(SearchType searchType, Integer matchPercentage) {
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
    SearchTypeMatch that = (SearchTypeMatch) o;
    return Objects.equals(matchPercentage, that.matchPercentage) &&
        searchType == that.searchType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(matchPercentage, searchType);
  }

  @Override
  public String toString() {
    return "SearchTypeMatch{" +
        "searchType=" + searchType +
        ", matchPercentage=" + matchPercentage +
        '}';
  }

  @Override
  public String getContextCategory() {
    return "SearchContext";
  }

  @Override
  public String getContextType() {
    return this.searchType.name();
  }

  @Override
  public Feasibility getFeasibility() {
    if (matchPercentage < 60) {
      return INFEASIBLE;
    }
    if (matchPercentage >= 60 && matchPercentage < 80) {
      return REASONABLE_STRETCH;
    }
    if (matchPercentage >= 80) {
      return PROBABLE;
    }
    return INFEASIBLE;
  }

  @Override
  public Object data() {
    return this.matchPercentage;
  }
}
