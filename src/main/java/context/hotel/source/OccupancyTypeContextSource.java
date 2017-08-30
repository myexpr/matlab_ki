package context.hotel.source;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import context.hotel.model.OccupancyType;
import context.hotel.model.SearchRequest;
import context.hotel.model.response.OccupancyTypeMatch;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OccupancyTypeContextSource implements ContextSource {

  public OccupancyType deterministicEvaluation(SearchRequest request) {
    OccupancyType matchedType = null;
    matchedType = asList(OccupancyType.values())
        .stream()
        .filter(t -> t.evaluate(request))
        .findFirst().get();
    return matchedType;
  }

  public List<OccupancyTypeMatch> deriveProbabilisticContext(SearchRequest request) {
    List<OccupancyTypeMatch> occpancyTypeMatches = asList(OccupancyType.values())
        .stream()
        .map(t -> t.probabilisticEvaluation(request))
        .filter(p -> p.getMatchPercentage() > 70)
        .collect(toList());
    return occpancyTypeMatches;
  }

  public List<OccupancyTypeMatch> deriveContext(SearchRequest request) {
    List<OccupancyTypeMatch> occpancyTypeMatches =
        asList(OccupancyType.values())
            .stream()
            .map(t -> t.weightedEvaluation(request))
            .filter(p -> p.getMatchPercentage() > 70)
            .sorted()
            .collect(toList());
    return occpancyTypeMatches;
  }

}
