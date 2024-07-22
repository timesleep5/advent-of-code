package day_11;

import _general.Day;

public class DayEleven extends Day {
    private final UniverseManager universeManager;

    DayEleven() {
        universeManager = new UniverseManager(input);
    }

    public static void main(String[] args) {
        DayEleven dayEleven = new DayEleven();
        System.out.println("Part I result: " + dayEleven.partOne());
        System.out.println("Part II result: " + dayEleven.partTwo());
    }

    @Override
    public int partOne() {
        resultPartOne = universeManager.getShortestPathSum();
        return resultPartOne; //19_254_834 too high
    }

    @Override
    public int partTwo() {
        return 0;
    }
}
