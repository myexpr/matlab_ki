package _con.text.hotel.engine;

import static java.util.Arrays.asList;

import _con.text.hotel.model.SearchRequest;
import java.util.List;
import java.util.stream.Collectors;

public class ContextEngine {

  public List<SearchType> evaluate(SearchRequest request) {
    List<SearchType> matchedTypes = null;
    matchedTypes = asList(SearchType.values())
        .stream()
        .filter(t -> {
          return t.evaluate(request);
        })
        .collect(Collectors.toList());
    return matchedTypes;
  }


}
