package _con.text.hotel.model;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class ContextEngine {

  public SearchType evaluate(SearchRequest request) {
    SimpleSearchRequest aSimpleSearch = (SimpleSearchRequest) request;
    if (aSimpleSearch.numberOfRooms() == 1 && aSimpleSearch.singleOccupancy()
        && aSimpleSearch.numberOfNights() == 1) {
      return SearchType.BUSINESS;
    }
    return null;
  }


}
