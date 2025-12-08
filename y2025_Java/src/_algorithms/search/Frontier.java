package _algorithms.search;

import java.util.List;

public interface Frontier {
    List<State> pop();

    void push(List<State> path);

    boolean isEmpty();
}
