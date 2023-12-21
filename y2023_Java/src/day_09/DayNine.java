package day_09;

import _general.Day;

import java.util.Arrays;

public class DayNine extends Day {
    private final int[][] historyValues;

    public static void main(String[] args) {
        DayNine dayNine = new DayNine();
        System.out.println("Part I result: " + dayNine.partOne());
        System.out.println("Part II result: " + dayNine.partTwo());
    }

    DayNine() {
        historyValues = getHistoryValues();
    }

    @Override
    public int partOne() {
        resultPartOne = Math.toIntExact(sumUpAllExtrapolatedValues());
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        resultPartTwo = Math.toIntExact(sumUpAllExtrapolatedBackwardsValues());
        return resultPartTwo;
    }

    private long sumUpAllExtrapolatedValues() {
        long sum = 0;
        for (int[] values : historyValues) {
            sum += new History(values).getLastLayer().extrapolateNewValue(0);
        }
        return sum;
    }

    private long sumUpAllExtrapolatedBackwardsValues() {
        long sum = 0;
        for (int[] values : historyValues) {
            sum += new History(values).getLastLayer().extrapolateNewBackwardsValue(0);
        }
        return sum;
    }

    private int[][] getHistoryValues() {
        int[][] historyValues = new int[input.length][];
        String[] stringValues;
        for (int line = 0; line < input.length; line++) {
            stringValues = input[line].strip().split(" ");
            historyValues[line] = intsFromStringArray(stringValues);
        }
        return historyValues;
    }

    private int[] intsFromStringArray(String[] stringValues) {
        return Arrays.stream(stringValues).mapToInt(Integer::parseInt).toArray();
    }
}
