package day01;

import _general.Day;

public class DayOne extends Day {
    private final CodeBreaker codeBreaker;

    public DayOne() {
        this.codeBreaker = new CodeBreaker(input);
    }

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        dayOne.printResults();
    }

    @Override
    protected String partOne() {
        var count = codeBreaker.countTargetNumberPositions();
        return String.valueOf(count);
    }

    @Override
    protected String partTwo() {
        var count = codeBreaker.countTargetNumberPositionsIncludingInBetween();
        return String.valueOf(count);
    }
}
