package day04;

import _general.Day;

public class DayFour extends Day {
    private final Grid grid;

    public DayFour() {
        this.grid = new Grid(input);
    }

    public static void main(String[] args) {
        var day = new DayFour();
        day.printResults();
    }

    @Override
    protected String partOne() {
        var accessibleRolls = grid.countAccessibleRolls();
        return String.valueOf(accessibleRolls);
    }

    @Override
    protected String partTwo() {
        var accessibleRolls = grid.countAccessibleRollsWithRemoval();
        return String.valueOf(accessibleRolls);
    }
}
