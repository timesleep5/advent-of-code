package day06;

import java.util.List;

public abstract class Equation {
    protected final List<Long> elements;

    protected Equation(List<Long> elements) {
        this.elements = elements;
    }

    abstract long evaluate();
}
