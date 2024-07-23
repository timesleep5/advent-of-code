package day_11;

import _general.Day;

import java.math.BigInteger;

public class DayEleven extends Day {

    public static void main(String[] args) {
        DayEleven dayEleven = new DayEleven();
        dayEleven.partOne();
        dayEleven.partTwo();
    }

    @Override
    public int partOne() {
        int expansionFactor = 2;
        UniverseManager universeManager = new UniverseManager(input, expansionFactor);
        BigInteger result = universeManager.getShortestPathSum();
        System.out.println("Part I result: " + result);
        return 0;
    }

    @Override
    public int partTwo() {
        int expansionFactor = 1_000_000;
        UniverseManager universeManager = new UniverseManager(input, expansionFactor);
        BigInteger result = universeManager.getShortestPathSum();
        System.out.println("Part II result: " + result);
        return 0;
    }
}
