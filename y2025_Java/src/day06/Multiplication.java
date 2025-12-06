package day06;

import java.util.List;

public class Multiplication extends Equation {
    protected Multiplication(List<Long> elements) {
        super(elements);
    }

    @Override
    public long evaluate() {
        return elements.stream().reduce(1L, (a, b) -> a * b);
    }
}
