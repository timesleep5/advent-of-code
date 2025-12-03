package day02;

import _general.Day;

public class DayTwo extends Day {
    private final InvalidIdFinder invalidIdFinder;

    public DayTwo() {
        this.invalidIdFinder = new InvalidIdFinder(input);
    }

    public static void main(String[] args) {
        var dayTwo = new DayTwo();
        dayTwo.printResults();
    }

    @Override
    protected String partOne() {
        return invalidIdFinder.sumUpInvalidIds();
    }

    @Override
    protected String partTwo() {
        return invalidIdFinder.sumUpInvalidStrictIds(); // 66500951798
    }
}
