package _algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class Search {
    protected abstract Frontier getFrontier(List<State> startStates);

    public List<List<State>> SearchAll(List<State> startStates) {
        var foundPaths = new ArrayList<List<State>>();
        var frontier = getFrontier(startStates);
        var exploredStates = new HashSet<State>();
        while (!frontier.isEmpty()) {
            var path = frontier.pop();
            var currentState = path.getLast();
            if (currentState.isGoal()) {
                foundPaths.add(path);
                continue;
            }

            currentState.nextStates()
                    .stream()
                    .filter(state -> !exploredStates.contains(state))
                    .forEach(state -> {
                        exploredStates.add(state);
                        var newPath = new ArrayList<>(path);
                        newPath.add(state);
                        frontier.push(newPath);
                    });
        }
        return foundPaths;
    }
}
