package day_12;

import _general.Day;

public class DayTwelve extends Day {
    private final RecordsManager recordsManager;

    public DayTwelve() {
        this.recordsManager = new RecordsManager(input);
    }

    public static void main(String[] args) {
        DayTwelve dayTwelve = new DayTwelve();
        System.out.println("Part I result: " + dayTwelve.partOne());
        System.out.println("Part II result: " + dayTwelve.partTwo());
    }

    @Override
    public int partOne() {
        resultPartOne = recordsManager.numberOfValidAlignments();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        return 0;
    }
}
