package day_10;

import _general.Day;

public class DayTen extends Day {
    private final Maze maze;
    public static void main(String[] args) {
        DayTen dayTen = new DayTen();
        System.out.println("Part I result: " + dayTen.partOne());
        System.out.println("Part II result: " + dayTen.partTwo());
    }

    DayTen() {
        char[][] map = createMap();
        maze = new Maze(map);
    }

    @Override
    public int partOne() {
        resultPartOne = maze.getFarthestPipe();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        return 0;
    }

    char[][] createMap() {
        char[][] map = new char[input.length][];
        for (int line = 0; line < input.length; line++) {
            map[line] = input[line].toCharArray();
        }
        return map;
    }
}
