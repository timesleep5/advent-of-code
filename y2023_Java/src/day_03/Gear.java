package day_03;

import java.util.ArrayList;
import java.util.List;

public class Gear {
    private final EngineMatrix engineMatrix;
    private List<Integer> partNumbers;

    private boolean newNumberPossible;

    Gear(EngineMatrix engineMatrix, int x, int y) {
        this.engineMatrix = engineMatrix;
        partNumbers = new ArrayList<>();
        findDistinctPartNumbers(x, y);
    }

    private void findDistinctPartNumbers(int x, int y) {
        for (int yValue = y - 1; yValue <= y + 1; yValue++) {
            newNumberPossible = true;
            for (int xValue = x - 1; xValue <= x + 1; xValue++) {
                if (engineMatrix.withinBoundaries(xValue, yValue)) {
                    lookForFinishedPartNumber(xValue, yValue);
                }
            }
        }
    }

    private void lookForFinishedPartNumber(int x, int y) {
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
        while (engineMatrix.withinBoundaries(xValue, y) && Character.isDigit(engineMatrix.getPosition(xValue, y))) {
            numberBuilder.append(engineMatrix.getPosition(xValue, y));
            xValue++;
        }
        return Integer.parseInt(numberBuilder.toString());
    }

    private int findStartX(int x, int y) {
        int startX = x;
        for (int checkedX = x; checkedX >= 0; checkedX--) {
            if (Character.isDigit(engineMatrix.getPosition(checkedX, y))) {
                startX = checkedX;
            } else {
                break;
            }
        }
        return startX;
    }

    int getGearRatio() {
        return partNumbers.get(0) * partNumbers.get(1);
    }
}
