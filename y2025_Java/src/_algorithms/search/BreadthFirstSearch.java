package _algorithms.search;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch extends Search {

    @Override
    protected Frontier getFrontier(List<State> startStates) {
        var frontier = new FrontierQueue();
        var startPaths = startStates
                .stream()
                .map(state -> new ArrayList<>(List.of(state)))
                .toList();
        frontier.addAll(startPaths);
        return frontier;
    }
}
