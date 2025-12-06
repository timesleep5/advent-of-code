package day06;

import java.util.List;

public class Addition extends Equation {
    protected Addition(List<Long> elements) {
        super(elements);
    }

    @Override
    public long evaluate() {
        return elements.stream().reduce(0L, Long::sum);
    }
}
