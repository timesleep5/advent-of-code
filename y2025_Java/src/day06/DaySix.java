package day06;

import _general.Day;

public class DaySix extends Day {
    private final HomeworkSolver homeworkSolver;

    public DaySix() {
        this.homeworkSolver = new HomeworkSolver(input);
    }

    public static void main(String[] args) {
        var day = new DaySix();
        day.printResults();
    }

    @Override
    protected String partOne() {
        return homeworkSolver.solveGrandTotal();
    }

    @Override
    protected String partTwo() {
        return homeworkSolver.solveRightToLeftGrandTotal();
    }
}
