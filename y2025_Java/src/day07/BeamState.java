package day07;

import _algorithms.search.State;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record BeamState(int row, int col, TachyonManifold tachymanifold) implements State {
    private static int splitCounter;
    private static final HashMap<BeamState, Long> timelineRegistry = new HashMap<>();

    @Override
    public List<State> nextStates() {
        if (isGoal()) {
            return List.of();
        } else if (tachymanifold.nextStepIsSplit(this)) {
            splitCounter++;
            var left = from(row + 1, col - 1);
            var right = from(row + 1, col + 1);
            return listFrom(left, right);
        }
        return listFrom(from(row + 1, col));
    }

    private BeamState from(int row, int col) {
        var newState = new BeamState(row, col, tachymanifold);
        updateTimelines(newState);
        return newState;
    }

    private void updateTimelines(BeamState newState) {
        // for the first state
        if (!timelineRegistry.containsKey(this)) {
            timelineRegistry.put(this, 1L);
        }
        var ownNumberOfTimelines = timelineRegistry.get(this);
        if (timelineRegistry.containsKey(newState)) {
            timelineRegistry.compute(newState, (k, existingNumberOfTimelines) -> existingNumberOfTimelines + ownNumberOfTimelines);
        } else {
            timelineRegistry.put(newState, ownNumberOfTimelines);
        }
    }

    private List<State> listFrom(BeamState... states) {
        return new ArrayList<>(List.of(states));
    }

    @Override
    public boolean isGoal() {
        return tachymanifold.nextStepExits(this);
    }

    static void reset() {
        splitCounter = 0;
        timelineRegistry.clear();
    }

    static int getSplitCounter() {
        return splitCounter;
    }

    static String getTotalNumberOfTimelines() {
        return timelineRegistry
                .keySet()
                .stream()
                .filter(BeamState::isGoal)
                .map(timelineRegistry::get)
                .map(String::valueOf)
                .map(BigInteger::new)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .toString();
    }
}
