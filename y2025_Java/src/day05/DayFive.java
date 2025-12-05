package day05;

import _general.Day;

public class DayFive extends Day {
    private final Inventory inventory;

    public DayFive() {
        this.inventory = new Inventory(input);
    }

    public static void main(String[] args) {
        var day = new DayFive();
        day.printResults();
    }

    @Override
    protected String partOne() {
        var count = inventory.countFreshAvailableIngredients();
        return String.valueOf(count);
    }

    @Override
    protected String partTwo() {
        var sum = inventory.sumUpRangeSizes();
        return String.valueOf(sum);
    }
}
