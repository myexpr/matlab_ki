package _con.text.hotel.engine;

import static _con.text.hotel.engine.SearchType.BUSINESS;

import _con.text.hotel.model.SearchRequest;

public class ContextEngine {

  public SearchType evaluate(SearchRequest request) {
    if (BUSINESS.evaluate(request)) {
      return BUSINESS;
    }
    return null;
  }


}
