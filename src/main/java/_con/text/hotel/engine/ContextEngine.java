package _con.text.hotel.engine;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import _con.text.hotel.model.SearchRequest;
import java.util.List;

public class ContextEngine {

  public SearchType deterministicEvaluation(SearchRequest request) {
    SearchType matchedType = null;
    matchedType = asList(SearchType.values())
        .stream()
        .filter(t -> {
          return t.evaluate(request);
        })
        .findFirst().get();
    return matchedType;
  }


  public List<PartialMatch> probabilisticMatch(SearchRequest request) {
    List<PartialMatch> partialMatches = asList(SearchType.values())
        .stream()
        .map(t -> {
          return t.probabilisticEvaluation(request);
        })
        .filter(p -> {
          return p.getProbability() > 70;
        })
        .collect(toList());
    return partialMatches;
  }
}
