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
        char[][] map = new MazeParser(input).createMap();
        maze = new Maze(map);
    }

    @Override
    public int partOne() {
        resultPartOne = maze.getFarthestPipe();
        return resultPartOne;
    }

    @Override
    public int partTwo() {
        resultPartTwo = maze.countEnclosedTiles();
        return resultPartTwo; //3871 too high, not 814
    }
}
