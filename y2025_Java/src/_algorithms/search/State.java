package _algorithms.search;

import java.util.List;

public interface State {
    List<State> nextStates();

    boolean isGoal();

    int hashCode();

    boolean equals(Object other);
}
