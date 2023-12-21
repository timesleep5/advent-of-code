package day_08;

import _general.Day;

import java.util.Arrays;

class DayEight extends Day {
    private final char[] instructions;
    private final Map map;

    public static void main(String[] args) {
        DayEight dayEight = new DayEight();
        System.out.println("Part I result: " + dayEight.partOne());
        dayEight.partTwo();
    }

    DayEight() {
        instructions = getInstructions();
        map = new Map(Arrays.copyOfRange(input, 2, input.length));
    }

    @Override
    public int partOne() {
        long startTime = System.nanoTime();
        resultPartOne = countInstructionsToExit();
        System.out.println(System.nanoTime() - startTime + "ns");
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        long result = countInstructionsToExitForAllNodes();
        System.out.println("Part II result: " + result);
        return 0;
    }

    private int countInstructionsToExit() {
        int totalRounds = map.executeInstructionsAndGetRounds(instructions);
        return totalRounds * instructions.length;
    }

    private long countInstructionsToExitForAllNodes() {
        int[] cycleLengths = map.countRoundsOfInstructionsForAllNodes(instructions);
        long multipleOfCycles = getMultiple(cycleLengths);
        return multipleOfCycles * instructions.length;
    }

    private long getMultiple(int[] numbers) {
        long multiple = 1;
        for (int number : numbers) {
            multiple *= number;
        }
        return multiple;
    }

    private char[] getInstructions() {
        return input[0].strip().toCharArray();
    }
}
