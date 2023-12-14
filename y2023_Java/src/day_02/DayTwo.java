package day_02;

import _general.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayTwo extends Day {
    private final COLOR[] colors = new COLOR[] {COLOR.RED, COLOR.GREEN, COLOR.BLUE};
    public static void main(String[] args) {
        DayTwo dayTwo = new DayTwo();
        System.out.println("Part I result: " + dayTwo.partOne());
        System.out.println("Part II result: " + dayTwo.partTwo());
    }

    @Override
    public int partOne() {
        resultPartOne = sumUpPossibleGameIDs();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        resultPartTwo = sumUpGamePowers();
        return resultPartTwo;
    }

    private int sumUpGamePowers() {
        int gamePowersSum= 0;
        for (String line : input) {
            List<CubeAmount> cubeAmounts = createCubeAmounts(line);
            gamePowersSum += getGamePower(cubeAmounts);
        }
        return gamePowersSum;
    }

    private int getGamePower(List<CubeAmount> cubeAmounts) {
        List<List<Integer>> allColorAmounts = new ArrayList<>();
        for (COLOR color:colors) {
            allColorAmounts.add(collectAmountsOfOneColor(cubeAmounts, color));
        }
        return computeGamePower(allColorAmounts);
    }

    private int computeGamePower(List<List<Integer>> allColorAmounts) {
        int power = 1;
        for (List<Integer> amountsOfColor: allColorAmounts) {
            power *= Collections.max(amountsOfColor);
        }
        return power;
    }

    private List<Integer> collectAmountsOfOneColor(List<CubeAmount> cubeAmounts, COLOR color) {
        List<Integer> amountsOfColor = new ArrayList<>();
        for (CubeAmount cubeAmount: cubeAmounts) {
            if (cubeAmount.color() == color) {
                amountsOfColor.add(cubeAmount.amount());
            }
        }
        System.out.println(amountsOfColor);
        return amountsOfColor;
    }

    private int sumUpPossibleGameIDs() {
        int possibleGameIdSum = 0;
        for (String line : input) {
            List<CubeAmount> cubeAmounts = createCubeAmounts(line);
            if (gameRoundPossible(cubeAmounts)) {
                possibleGameIdSum += getGameID(line);
            }
        }
        return possibleGameIdSum;
    }

    private List<CubeAmount> createCubeAmounts(String line) {
        String[] gameRounds = splitLineIntoGames(line);
        return createCubeAmountsFromGameRounds(gameRounds);
    }

    private boolean gameRoundPossible(List<CubeAmount> cubeAmounts) {
        boolean possible = true;
        for (CubeAmount cubeAmount : cubeAmounts) {
            if (!cubeAmount.possible()) {
                possible = false;
                break;
            }
        }
        return possible;
    }

    private List<CubeAmount> createCubeAmountsFromGameRounds(String[] gameRounds) {
        List<CubeAmount> cubeAmounts = new ArrayList<>();
        for (String gameRound : gameRounds) {
            gameRound = gameRound.strip();
            int amount = Integer.parseInt(gameRound.split(" ")[0]);
            String color = gameRound.split(" ")[1];
            cubeAmounts.add(buildCube(color, amount));
        }
        return cubeAmounts;
    }

    private CubeAmount buildCube(String color, int amount) {
        return switch (color) {
            case "red" -> new CubeAmount(COLOR.RED, amount);
            case "green" -> new CubeAmount(COLOR.GREEN, amount);
            case "blue" -> new CubeAmount(COLOR.BLUE, amount);
            default -> null;
        };
    }

    private int getGameID(String line) {
        return Integer.parseInt(line.split(":")[0].split(" ")[1]);
    }

    private String[] splitLineIntoGames(String line) {
        return line.split(":")[1].split("[;,]");
    }
}
