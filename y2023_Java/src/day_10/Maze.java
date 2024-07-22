package day_10;

public class Maze {
    private final char[][] map;
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
}
