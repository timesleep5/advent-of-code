package day_06;

import _general.Day;

import java.util.Arrays;

public class DaySix extends Day {
    private final int mmPerMsIncreasePerMs = 1;
    private final Race[] races;
    private final int[] timesInMs;
    private final int[] distancesInMm;

    public static void main(String[] args) {
        DaySix daySix = new DaySix();
        System.out.println("Part I result: " + daySix.partOne());
        System.out.println("Part II result: " + daySix.partTwo());
    }

    DaySix() {
        timesInMs = getNumbers(true);
        distancesInMm = getNumbers(false);
        races = getRaces();
    }

    @Override
    public int partOne() {
        resultPartOne = getNumberOfWaysProduct(races);
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        Race bigRace = getBigRace();
        resultPartTwo = getNumberOfWaysProduct(new Race[]{bigRace});
        return resultPartTwo;
    }

    private Race getBigRace() {
        long totalRaceTime = appendToOneNumber(timesInMs);
        long totalDistance = appendToOneNumber(distancesInMm);
        return new Race(totalRaceTime, totalDistance);
    }

    private long appendToOneNumber(int[] numberArray) {
        StringBuilder bigNumber = new StringBuilder();
        for (int number : numberArray) {
            bigNumber.append(String.valueOf(number));
        }
        return Long.parseLong(bigNumber.toString());
    }

    private int getNumberOfWaysProduct(Race[] races) {
        int numberOfWaysProduct = 1;
        for (Race race : races) {
            numberOfWaysProduct *= getNumberOfWaysForRace(race);
        }
        return numberOfWaysProduct;
    }

    private int getNumberOfWaysForRace(Race race) {
        int numberOfWays = 0;
        for (int holdTimeInMs = 0; holdTimeInMs < race.timeInMs(); holdTimeInMs++) {
            if (computeTotalDistanceForHoldTime(holdTimeInMs, race.timeInMs()) > race.distanceInMm()) {
                numberOfWays++;
            }
        }
        return numberOfWays;
    }

    private long computeTotalDistanceForHoldTime(long holdTimeInMs, long totalTimeInMs) {
        long runTime = totalTimeInMs - holdTimeInMs;
        long mmPerMs = holdTimeInMs * mmPerMsIncreasePerMs;
        return runTime * mmPerMs;
    }

    private Race[] getRaces() {
        Race[] races = new Race[timesInMs.length];
        for (int raceIndex = 0; raceIndex < timesInMs.length; raceIndex++) {
            races[raceIndex] = new Race(timesInMs[raceIndex], distancesInMm[raceIndex]);
        }
        return races;
    }

    private int[] getNumbers(boolean times) {
        int index = times ? 0 : 1;
        String[] strings = input[index].split(":")[1].strip().replaceAll("\\s+", "_").split("_");
        return Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
    }
}
