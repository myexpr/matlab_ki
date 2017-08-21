package context.hotel.engine;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import context.hotel.model.SearchRequest;
import context.hotel.model.SearchType;
import context.hotel.model.response.SearchTypeMatch;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SearchTypeContextSource implements ContextSource {

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


  public List<SearchTypeMatch> deriveContext(SearchRequest request) {
    List<SearchTypeMatch> searchTypeMatches = asList(SearchType.values())
        .stream()
        .map(t -> {
          return t.probabilisticEvaluation(request);
        })
        .filter(p -> {
          return p.getMatchPercentage() > 70;
        })
        .collect(toList());
    return searchTypeMatches;
  }
}
