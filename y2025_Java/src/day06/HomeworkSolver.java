package day06;

import _general.Input;

import java.math.BigInteger;
import java.util.List;

public class HomeworkSolver {
    private final List<Equation> equations;
    private final List<Equation> rightToLeftEquations;

    HomeworkSolver(Input input) {
        var parser = new Parser(input);
        equations = parser.parseEquations();
        rightToLeftEquations = parser.parseRightToLeftEquations();
    }

    String solveGrandTotal() {
        return solveEquations(equations);
    }

    String solveRightToLeftGrandTotal() {
        return solveEquations(rightToLeftEquations);
    }

    private String solveEquations(List<Equation> equations) {
        return equations
                .stream()
                .map(Equation::evaluate)
                .map(String::valueOf)
                .map(BigInteger::new)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .toString();
    }
}
