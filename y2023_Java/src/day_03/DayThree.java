package day_03;


import _general.Day;

import java.util.ArrayList;
import java.util.List;

public class DayThree extends Day {
    private final int PART_NUMBER_COUNT_FOR_GEAR = 2;
    private final EngineMatrix engineMatrix;
    private boolean onNumber;
    private boolean newNumberPossible;
    private int partNumberSum;
    private int partNumberCount;
    private int startX;
    private int length;

    public DayThree() {
        engineMatrix = new EngineMatrix(input);
        onNumber = false;
        newNumberPossible = true;
        partNumberSum = 0;
        partNumberCount = 0;
        startX = 0;
        length = 0;
    }

    public static void main(String[] args) {
        DayThree dayThree = new DayThree();
        System.out.println("Part I result: " + dayThree.partOne());
        System.out.println("Part II result: " + dayThree.partTwo());
    }

    @Override
    public int partOne() {
        resultPartOne = sumUpAllPartNumbers();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        resultPartTwo = sumUpAllGearRatios();
        return resultPartTwo;
        // 1_205_517_858 too high
        // 78_301_689 too low
    }

    private int sumUpAllGearRatios() {
        int gearRatioSum = 0;
        List<Gear> gears = findGears();
        for (Gear gear : gears) {
            gearRatioSum += gear.getGearRatio();
        }
        return gearRatioSum;
    }

    private List<Gear> findGears() {
        List<Gear> gears = new ArrayList<>();
        for (int y = 0; y < engineMatrix.getHeight(); y++) {
            for (int x = 0; x < engineMatrix.getWidth(y); x++) {
                if (isGear(x, y)) {
                    gears.add(new Gear(engineMatrix, x, y));
                }
            }
        }
        return gears;
    }

    private boolean isGear(int x, int y) {
        return engineMatrix.getPosition(x, y) == '*' && countAdjacentPartNumbers(x, y) == PART_NUMBER_COUNT_FOR_GEAR;
    }

    private int countAdjacentPartNumbers(int x, int y) {
        partNumberCount = 0;
        for (int yValue = y - 1; yValue <= y + 1; yValue++) {
            newNumberPossible = true;
            for (int xValue = x - 1; xValue <= x + 1; xValue++) {
                if (engineMatrix.withinBoundaries(xValue, yValue)) {
                    incrementCounterIfNewNumberStarts(xValue, yValue);
                }
            }
        }
        return partNumberCount;
    }

    private void incrementCounterIfNewNumberStarts(int x, int y) {
        if (!Character.isDigit(engineMatrix.getPosition(x, y))) {
            newNumberPossible = true;
        } else if (newNumberPossible) {
            partNumberCount++;
            newNumberPossible = false;
        }
    }

    private int sumUpAllPartNumbers() {
        for (int y = 0; y < engineMatrix.getHeight(); y++) {
            for (int x = 0; x < engineMatrix.getWidth(y); x++) {
                processPosition(x, y);
            }
        }
        return partNumberSum;
    }

    private void processPosition(int x, int y) {
        if (Character.isDigit(engineMatrix.getPosition(x, y))) {
            if (!onNumber) {
                initializePartNumberTracking(x);
            } else {
                continueOrEndPartNumber(x, y);
            }
        } else {
            addPartNumberToSumIfFinished(startX, y, length);
        }
    }

    private void initializePartNumberTracking(int x) {
        onNumber = true;
        length = 1;
        startX = x;
    }

    private void continueOrEndPartNumber(int x, int y) {
        if (!engineMatrix.withinBoundaries(x + 1, y)) {
            addPartNumberToSumIfFinished(startX, y, length + 1);
        } else {
            length++;
        }
    }

    private void addPartNumberToSumIfFinished(int startX, int y, int length) {
        if (onNumber) {
            onNumber = false;
            if (isPartNumber(startX, y, length)) {
                partNumberSum += convertCharArrayToNumber(startX, y, length);
            }
        }
    }

    private int convertCharArrayToNumber(int startX, int y, int length) {
        StringBuilder numberAsString = new StringBuilder();
        for (int x = startX; x < startX + length; x++) {
            numberAsString.append(engineMatrix.getPosition(x, y));
        }
        return Integer.parseInt(numberAsString.toString());
    }

    private boolean isPartNumber(int x, int y, int length) {
        for (int yValue = y - 1; yValue < y + 2; yValue++) {
            for (int xValue = x - 1; xValue < x + length + 1; xValue++) {
                if (engineMatrix.withinBoundaries(xValue, yValue) && isEnginePart(xValue, yValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isEnginePart(int x, int y) {
        return !Character.isDigit(engineMatrix.getPosition(x, y)) && engineMatrix.getPosition(x, y) != '.';
    }
}
