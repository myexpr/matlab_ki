package context.hotel.engine;

import context.hotel.model.SearchRequest;
import context.hotel.model.response.ContextMatch;
import context.hotel.source.ContextSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by araman on 20/08/2017.
 */
@Component
public class ContextEngine {

  @Autowired
  List<ContextSource> contextSources;

  public Map<String, ? extends List<? extends ContextMatch>> process(SearchRequest request) {
    Map<String, ? extends List<? extends ContextMatch>> collectedContext = contextSources
        .stream()
        .flatMap(s -> s.deriveContext(request).stream())
        .collect(Collectors.groupingBy(ContextMatch::getContextCategory));

    return collectedContext;
  }

  public List<? extends ContextMatch> processAsList(SearchRequest request) {
    List<? extends ContextMatch> collectedContext = contextSources
        .stream()
        .flatMap(s -> s.deriveContext(request).stream())
        .collect(Collectors.toList());

    return collectedContext;
  }


}
