package day_05;

import _general.Day;

import java.util.*;

public class DayFive extends Day {
    private final long[] seedsPartOne;
    private final Map firstMap;

    public static void main(String[] args) {
        DayFive dayFive = new DayFive();
        System.out.println("Part I result: " + dayFive.partOne());
        System.out.println("Part II result: " + dayFive.partTwo());
    }

    public DayFive() {
        seedsPartOne = getSeedsPartOne();
        firstMap = createMaps();
    }

    @Override
    public int partOne() {
        long smallestLocation = getSmallestLocationPartOne(seedsPartOne);
        return Math.toIntExact(smallestLocation);
    }

    @Override
    public int partTwo() {
        long smallestLocation = getSmallestLocationPartTwo();
        return Math.toIntExact(smallestLocation);
    }

    private long getSmallestLocationPartOne(long[] seeds) {
        List<Long> locations = new ArrayList<>();
        for (long seed : seeds) {
            locations.add(firstMap.mapValue(seed));
        }
        return Collections.min(locations);
    }

    private long getSmallestLocationPartTwo() {
        long smallestLocation = Long.MAX_VALUE;
        for (int seedIndex = 0; seedIndex < seedsPartOne.length; seedIndex += 2) {
            System.out.println((int)((seedIndex / (float) seedsPartOne.length) * 100) + "%");
            smallestLocation = updateSmallestLocation(smallestLocation, seedIndex);
        }
        return smallestLocation;
    }

    private long updateSmallestLocation(long smallestLocation, int seedIndex) {
        long startSeed = seedsPartOne[seedIndex];
        long seedLimit = Math.addExact(startSeed, seedsPartOne[seedIndex + 1]);
        long currentLocation;
        for (long seed = startSeed; seed < seedLimit; seed++) {
            currentLocation = firstMap.mapValue(seed);
            smallestLocation = Math.min(smallestLocation, currentLocation);
        }
        return smallestLocation;
    }

    private long[] getSeedsPartOne() {
        String[] seedStrings = input[0].strip().split(": ")[1].split(" ");
        return Arrays.stream(seedStrings).mapToLong(Long::parseLong).toArray();
    }

    private Map createMaps() {
        Map firstMap = new Map();
        Map currentMap = firstMap;
        String line;
        for (int lineIndex = 3; lineIndex < input.length; lineIndex++) {
            line = input[lineIndex];
            if (!line.isBlank() && Character.isDigit(line.charAt(0))) {
                currentMap.addMapping(line);
            } else {
                currentMap.successor = new Map();
                currentMap = currentMap.successor;
            }
        }
        return firstMap;
    }
}
