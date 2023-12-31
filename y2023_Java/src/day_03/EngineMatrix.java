package day_03;

import java.sql.SQLOutput;

public class EngineMatrix {
    private final char[][] matrix;

    EngineMatrix(String[] input) {
        matrix = generateEngineMatrixFromInput(input);
    }

    private char[][] generateEngineMatrixFromInput(String[] input) {
        char[][] charMatrix = new char[input.length][input[0].length()];
        for (int line = 0; line < input.length; line++) {
            char[] charLine = input[line].toCharArray();
            charMatrix[line] = charLine;
        }
        return charMatrix;
    }

    boolean withinBoundaries(int x, int y) {
        return y >= 0 && y < matrix.length && x >= 0 && x < matrix[y].length;
    }

    char getPosition(int x, int y) {
        return matrix[y][x];
    }

    int getWidth(int y) {
        return matrix[y].length;
    }

    int getHeight() {
        return matrix.length;
    }
}
