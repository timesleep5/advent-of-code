package day_04;

public class Scratchcard implements Cloneable {
    private final int[] winningNumbers;
    private final int[] ownNumbers;
    private final int points;
    private final int order;
    private int numberOfCopies;

    Scratchcard(String line) {
        winningNumbers = getNumbers(line, false);
        ownNumbers = getNumbers(line, true);
        points = countMatches();
        order = getOrder(line);
        numberOfCopies = 1;
    }

    private int[] getNumbers(String line, boolean ownNumbers) {
        String[] numberStrings = ownNumbers ? getOwnNumberStrings(line) : getWinningNumberStrings(line);
        return parseNumberStrings(numberStrings);
    }

    private int countMatches() {
        int points = 0;
        for (int number : winningNumbers) {
            if (ownNumbersContain(number)) {
                points = points == 0 ? 1 : points * 2;
            }
        }
        return points;
    }

    private int getOrder(String line) {
        return Integer.parseInt(line.substring(5, 8).strip());
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

    private boolean ownNumbersContain(int number) {
        for (int ownNumber : ownNumbers) {
            if (ownNumber == number) {
                return true;
            }
        }
        return false;
    }

    int getPoints() {
        return points;
    }

    int getMatchingNumbersCount() {
        return (int) (Math.log(points) / Math.log(2) + 1);
    }

    int getOrder() {
        return order;
    }

    void addCopy() {
        numberOfCopies++;
    }

    int getNumberOfCopies() {
        return numberOfCopies;
    }
}
