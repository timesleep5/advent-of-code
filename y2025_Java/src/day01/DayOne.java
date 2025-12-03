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
    protected int partOne() {
        return codeBreaker.countTargetNumberPositions();
    }

    @Override
    protected int partTwo() {
        return codeBreaker.countTargetNumberPositionsIncludingInBetween(); // 5987 too high
    }
}
