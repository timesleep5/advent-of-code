package day07;

import _general.Input;

import java.util.List;

class TachyonManifold {
    private static final char START = 'S';
    private static final char SPLITTER = '^';
    private final List<String> map;

    TachyonManifold(Input input) {
        this.map = input.lines();
    }

    BeamState getStartBeamState() {
        var line = map.getFirst();
        for (int col = 0; col < line.length(); col++) {
            if (line.charAt(col) == START) {
                return new BeamState(0, col, this);
            }
        }
        throw new IllegalArgumentException("No start state found");
    }

    boolean nextStepIsSplit(BeamState state) {
        if (nextStepExits(state)) throw new IllegalArgumentException("Next step is already exits");
        return map
                .get(state.row() + 1)
                .charAt(state.col())
                == SPLITTER;
    }

    boolean nextStepExits(BeamState state) {
        return state.row() == map.size() - 1;
    }
}
