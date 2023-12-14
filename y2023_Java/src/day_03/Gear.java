package day_03;

import java.util.ArrayList;
import java.util.List;

public class Gear {
    private final EngineMatrix engineMatrix;
    private List<Integer> partNumbers;

    private boolean newNumberPossible;

    Gear(EngineMatrix engineMatrix, int x, int y) {
        this.engineMatrix = engineMatrix;
        findDistinctPartNumbers(x, y);
    }

    private void findDistinctPartNumbers(int x, int y) {
        partNumbers = new ArrayList<>();
        newNumberPossible = true;
        for (int yValue = y - 1; yValue < y + 2; yValue++) {
            for (int xValue = x - 1; xValue < x + 2; xValue++) {
                if (engineMatrix.withinBoundaries(xValue, yValue)) {
                    addPartNumberIfFinished(xValue, yValue);
                }
            }
            newNumberPossible = true;
        }
    }

    private void addPartNumberIfFinished(int x, int y) {
        if (!Character.isDigit(engineMatrix.getPosition(x, y))) {
            newNumberPossible = true;
        } else if (newNumberPossible) {
            partNumbers.add(findPartNumberAround(x, y));
            newNumberPossible = false;
        }
    }

    private int findPartNumberAround(int x, int y) {
        StringBuilder numberBuilder = new StringBuilder();
        int xValue = findStartX(x, y);
        while (Character.isDigit(engineMatrix.getPosition(xValue, y))) {
            numberBuilder.append(engineMatrix.getPosition(xValue, y));
            xValue++;
        }
        return Integer.parseInt(numberBuilder.toString());
    }

    private int findStartX(int x, int y) {
        for (int xValue = x; xValue >= 1; xValue--) {
            if (!Character.isDigit(engineMatrix.getPosition(xValue - 1, y))) {
                return xValue;
            }
        }
        return x;
    }

    int getGearRatio() {
        return partNumbers.get(0) * partNumbers.get(1);
    }
}
