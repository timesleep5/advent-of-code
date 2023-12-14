package day_04;

import _general.Day;


public class DayFour extends Day {
    private int[][] winningNumbers;
    private int[][] ownNumbers;

    public static void main(String[] args) {
        DayFour dayFour = new DayFour();
        System.out.println("Part I result: " + dayFour.partOne());
        System.out.println("Part II result: " + dayFour.partTwo());
    }

    public DayFour() {
        winningNumbers = getNumbers(false);
        ownNumbers = getNumbers(true);
    }

    @Override
    public int partOne() {
        sumUpAllPoints();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        return 0;
    }

    private int[][] getNumbers(boolean ownNumbers) {
        int[][] numbers = new int[input.length][];
        String[] numberStrings;
        for (int line = 0; line < input.length; line++) {
            numberStrings = ownNumbers ? getOwnNumberStrings(input[line]) : getWinningNumberStrings(input[line]);
            numbers[line] = parseNumberStrings(numberStrings);
        }
        return numbers;
    }

    private String[] getWinningNumberStrings(String line) {
        return line.split(" \\| ")[0].split(":")[1].split("(?<=\\G.{" + 3 + "})");
    }

    private String[] getOwnNumberStrings(String line) {
        return line.split(" \\|")[1].split("(?<=\\G.{" + 3 + "})");
    }

    private int[] parseNumberStrings(String[] winningNumberStrings) {
        int[] winningNumbers = new int[winningNumberStrings.length];
        for (int number = 0; number < winningNumberStrings.length; number++) {
            winningNumbers[number] = Integer.parseInt(winningNumberStrings[number].strip());
        }
        return winningNumbers;
    }

    private void sumUpAllPoints() {
        int pointsForLine;
        for (int line = 0; line < winningNumbers.length; line++) {
            pointsForLine = 0;
            for (int number : winningNumbers[line]) {
                if (ownNumbersContain(number, line)) {
                    pointsForLine = pointsForLine == 0 ? 1 : pointsForLine * 2;
                }
            }
            resultPartOne += pointsForLine;
        }
    }

    private boolean ownNumbersContain(int number, int line) {
        for (int ownNumber : ownNumbers[line]) {
            if (ownNumber == number) {
                return true;
            }
        }
        return false;
    }
}
