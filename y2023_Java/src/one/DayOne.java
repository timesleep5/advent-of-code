package one;

import general.Day;

import java.util.Arrays;
import java.util.List;

public class DayOne extends Day {
    private final List<String> digitsAsWords = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private boolean partOne;

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        System.out.println("Part I result: " + dayOne.partOne());
        System.out.println("Part II result: " + dayOne.partTwo());
    }

    @Override
    public int partOne() {
        partOne = true;
        resultPartOne = computeSumOfAllNumbers();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        partOne = false;
        resultPartTwo = computeSumOfAllNumbers();
        return resultPartTwo;
    }

    private int computeSumOfAllNumbers() {
        int sum = 0;
        for (String line : input) {
            sum += buildNumberFromLine(line);
        }
        return sum;
    }

    private int buildNumberFromLine(String line) {
        StringBuilder allDigits = new StringBuilder();
        for (int letter = 0; letter < line.length(); letter++) {
            if (Character.isDigit(line.charAt(letter))) {
                allDigits.append(line.charAt(letter));
            } else {
                allDigits.append(partOne ? "" : checkForDigitsAsWords(line, letter));
            }
        }
        String number = getNumberFromDigits(allDigits);
        return Integer.parseInt(number);
    }

    private String checkForDigitsAsWords(String line, int lastLetter) {
        for (int firstLetter = lastLetter - 2; firstLetter >= 0; firstLetter--) {
            String word = line.substring(firstLetter, lastLetter + 1);
            if (digitsAsWords.contains(word)) {
                return convertWordToDigit(word);
            }
        }
        return "";
    }

    private String convertWordToDigit(String word) {
        return String.valueOf(digitsAsWords.indexOf(word) + 1);
    }

    private String getNumberFromDigits(StringBuilder allDigits) {
        String number = allDigits.substring(0, 1);
        number += allDigits.substring(allDigits.length() - 1, allDigits.length());
        return number;
    }
}
