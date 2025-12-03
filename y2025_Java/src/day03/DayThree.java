package day03;

import _general.Day;
import day02.InvalidIdFinder;

public class DayThree extends Day {
    private final PowerDistributor powerDistributor;

    public DayThree() {
        this.powerDistributor = new PowerDistributor(input);
    }

    public static void main(String[] args) {
        var dayTwo = new DayThree();
        dayTwo.printResults();
    }

    @Override
    protected String partOne() {
        var maxJoltageSum = powerDistributor.sumUpMaxJoltages(2);
        return String.valueOf(maxJoltageSum);
    }

    @Override
    protected String partTwo() {
        var maxJoltageSum = powerDistributor.sumUpMaxJoltages(12);
        return String.valueOf(maxJoltageSum);    }
}
