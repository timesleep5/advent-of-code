package day07;

import _algorithms.search.BreadthFirstSearch;
import _algorithms.search.Search;
import _algorithms.search.State;
import _general.Input;

import java.util.ArrayList;
import java.util.List;

class BeamSimulator {
    private final TachyonManifold tachymanifold;
    private final Search search;

    BeamSimulator(Input input) {
        tachymanifold = new TachyonManifold(input);
        search = new BreadthFirstSearch();
    }

    int simulateBeamSplits() {
        var startPosition = tachymanifold.getStartBeamState();
        var startPositions = new ArrayList<State>(List.of(startPosition));
        BeamState.reset();
        search.SearchAll(startPositions);
        return BeamState.getSplitCounter();
    }

    String simulateQuantumBeamSplits() {
        simulateBeamSplits();
        return BeamState.getTotalNumberOfTimelines();
    }
}
