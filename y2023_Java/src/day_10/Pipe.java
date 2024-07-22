package day_10;

public class Pipe {
    private static int pipeCounter = 0;
    private final boolean isStart;
    private final int rowIndex;
    private final int colIndex;
    private final CONNECTION_OUT connectionOut;
    private final Maze maze;

    Pipe(int rowIndex, int colIndex, Maze maze, CONNECTION_IN connectionIn) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.maze = maze;
        char symbol;
        if (maze.getSymbolAt(rowIndex, colIndex) == 'S') {
            isStart = true;
            symbol = getStartSymbol(rowIndex, colIndex);
        } else {
            isStart = false;
            symbol = maze.getSymbolAt(rowIndex, colIndex);
        }
        connectionOut = connectionIn.getCorrespondingConnectionOut(symbol);
        pipeCounter++;
    }

    static int getPipeCounter() {
        return pipeCounter;
    }

    Pipe buildNextPipe() {
        if (isStart && pipeCounter > 1) {
            return null;
        }

        CONNECTION_IN newConnectionIn = connectionOut.getCorrespondingConnectionIn();
        int newRowIndex = rowIndex;
        int newColIndex = colIndex;

        switch (connectionOut) {
            case TO_NORTH -> newRowIndex--;
            case TO_SOUTH -> newRowIndex++;
            case TO_WEST -> newColIndex--;
            case TO_EAST -> newColIndex++;
        }

        return new Pipe(newRowIndex, newColIndex, maze, newConnectionIn);
    }

    private char getStartSymbol(int rowIndex, int colIndex) {
        char north = maze.getSymbolAt(rowIndex - 1, colIndex);
        char south = maze.getSymbolAt(rowIndex + 1, colIndex);
        char west = maze.getSymbolAt(rowIndex, colIndex - 1);
        char east = maze.getSymbolAt(rowIndex, colIndex + 1);

        boolean connectionNorth = north == 'F' || north == '|' || north == '7';
        boolean connectionSouth = south == 'L' || south == '|' || south == 'J';
        boolean connectionWest = west == 'F' || west == '-' || west == 'L';
        boolean connectionEast = east == '7' || east == '-' || east == 'J';

        if (connectionNorth && connectionSouth) return '|';
        else if (connectionNorth && connectionWest) return 'J';
        else if (connectionNorth && connectionEast) return 'L';
        else if (connectionSouth && connectionWest) return '7';
        else if (connectionSouth && connectionEast) return 'F';
        else if (connectionWest && connectionEast) return '-';
        else
            throw new IllegalArgumentException("starting Pipe at " + rowIndex + ", " + colIndex + " has no connection");
    }
}
