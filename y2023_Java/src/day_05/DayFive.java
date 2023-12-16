package day_05;

import _general.Day;

import java.util.*;

public class DayFive extends Day {
    private final long[] seeds;
    private final Map firstMap;

    public static void main(String[] args) {
        DayFive dayFive = new DayFive();
        System.out.println("Part I result: " + dayFive.partOne());
        System.out.println("Part II result: " + dayFive.partTwo());
    }

    public DayFive() {
        seeds = getSeeds();
        firstMap = createMaps();
    }

    @Override
    public int partOne() {
        long smallestLocation = getSmallestLocation();
        return Math.toIntExact(smallestLocation);
    }

    @Override
    public int partTwo() {
        return 0;
    }

    private long getSmallestLocation() {
        List<Long> locations = new ArrayList<>();
        for (long seed: seeds) {
            locations.add(firstMap.mapValue(seed));
        }
        return Collections.min(locations);
    }

    private long[] getSeeds() {
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
