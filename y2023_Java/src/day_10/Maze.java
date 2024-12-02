package day_10;

import java.util.Arrays;

public class Maze {
    private static final char PIPE_SYMBOL = '*';
    private static final char EMPTY_TILE_SYMBOL = '.';
    private final char[][] map;
    private char[][] pipeMap;
    private final Pipe startPipe;

    Maze(char[][] map) {
        this.map = map;
        startPipe = getStartPipe();
    }

    int getFarthestPipe() {
        Pipe currentPipe = startPipe;
        while (currentPipe != null) {
            currentPipe = currentPipe.buildNextPipe();
        }
        return Pipe.getPipeCounter() / 2;
    }

    char getSymbolAt(int rowIndex, int colIndex) {
        if (withinBoundaries(rowIndex, colIndex)) {
            return map[rowIndex][colIndex];
        } else throw new ArrayIndexOutOfBoundsException("row: " + rowIndex + ", col: " + colIndex);
    }

    private Pipe getStartPipe() {
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            for (int colIndex = 0; colIndex < map[rowIndex].length; colIndex++) {
                if (map[rowIndex][colIndex] == 'S') {
                    return new Pipe(rowIndex, colIndex, this, getStartConnectionType(rowIndex, colIndex));
                }
            }
        }
        throw new IllegalArgumentException("No starting Pipe found!");
    }

    private boolean withinBoundaries(int rowIndex, int colIndex) {
        return 0 <= rowIndex && rowIndex < map.length && 0 <= colIndex && colIndex < map[rowIndex].length;
    }

    private CONNECTION_IN getStartConnectionType(int rowIndex, int colIndex) {
        char north = getSymbolAt(rowIndex - 1, colIndex);
        char south = getSymbolAt(rowIndex + 1, colIndex);
        char west = getSymbolAt(rowIndex, colIndex - 1);
        char east = getSymbolAt(rowIndex, colIndex + 1);

        if (north == 'F' || north == '|' || north == '7') return CONNECTION_IN.FROM_NORTH;
        else if (south == 'L' || south == '|' || south == 'J') return CONNECTION_IN.FROM_SOUTH;
        else if (west == 'F' || west == '-' || west == 'L') return CONNECTION_IN.FROM_WEST;
        else if (east == '7' || east == '-' || east == 'J') return CONNECTION_IN.FROM_WEST;
        else
            throw new IllegalArgumentException("starting Pipe at " + rowIndex + ", " + colIndex + " has no connection");
    }

    int countEnclosedTiles() {
        buildPipeMap();
        int count = 0;
        int rowLength = pipeMap.length;
        int colLength = pipeMap[0].length;
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                if (pipeMap[rowIndex][colIndex] == EMPTY_TILE_SYMBOL) {
                    int pipesNorth = pipesInColumn(colIndex, 0, rowIndex);
                    int pipesSouth = pipesInColumn(colIndex, rowIndex + 1, colLength);
                    int pipesWest = pipesInRow(rowIndex, 0, colIndex);
                    int pipesEast = pipesInRow(rowIndex, colIndex + 1, rowLength);

                    if ((pipesNorth % 2 == 1 || pipesSouth % 2 == 1 || pipesWest % 2 == 1 || pipesEast % 2 == 1)
                            && (pipesNorth > 1 && pipesSouth > 1 && pipesWest > 1 && pipesEast > 1)) {
                        pipeMap[rowIndex][colIndex] = 'I';
                        count++;
                    } else {
                        pipeMap[rowIndex][colIndex] = 'O';
                    }
                }
            }
        }
        printPipeMaze();
        return count;
    }

    private void buildPipeMap() {
        pipeMap = emptyMap();
        Pipe currentPipe = startPipe;
        while (currentPipe != null) {
            int row = currentPipe.getRowIndex();
            int col = currentPipe.getColIndex();
            pipeMap[row][col] = map[row][col];
            currentPipe = currentPipe.getNextPipe();
        }
        printPipeMaze();
    }

    private char[][] emptyMap() {
        char[][] emptyMap = new char[map.length][map[0].length];
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
            Arrays.fill(emptyMap[rowIndex], EMPTY_TILE_SYMBOL);
        }
        return emptyMap;
    }

    private int pipesInColumn(int colIndex, int start, int end) {
        if (end <= start) return 0;
        int count = 0;
        for (int i = start; i < end; i++) {
            if (pipeMap[i][colIndex] == '-') {
                count++;
            }
        }
        return count;
    }

    private int pipesInRow(int rowIndex, int start, int end) {
        if (end <= start) return 0;
        int count = 0;
        for (int i = start; i < end; i++) {
            if (pipeMap[rowIndex][i] == '|') {
                count++;
            }
        }
        return count;
    }

    private void printPipeMaze() {
        for (char[] line : pipeMap) {
            String printed = Arrays.toString(line).replace(", ", "");
            System.out.println(printed);
        }
        System.out.println();
    }
}
