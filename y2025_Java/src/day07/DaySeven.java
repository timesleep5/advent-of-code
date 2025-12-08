package day07;

import _general.Day;
import day06.HomeworkSolver;

public class DaySeven extends Day {
    private final BeamSimulator beamSimulator;

    public DaySeven() {
        this.beamSimulator = new BeamSimulator(input);
    }

    public static void main(String[] args) {
        var day = new DaySeven();
        day.printResults();
    }

    @Override
    protected String partOne() {
        var count = beamSimulator.simulateBeamSplits();
        return String.valueOf(count);
    }

    @Override
    protected String partTwo() {
        var count = beamSimulator.simulateQuantumBeamSplits();
        return String.valueOf(count);
    }
}
