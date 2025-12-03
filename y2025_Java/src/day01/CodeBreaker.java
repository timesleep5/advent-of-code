package day01;

import _general.Input;

public class CodeBreaker {
    private static final int TARGET_NUMBER = 0;
    private final Input input;
    private final Dial dial;
    private int targetNumberCount;

    CodeBreaker(Input input) {
        this.input = input;
        this.dial = new Dial();
    }

    void reset() {
        this.targetNumberCount = 0;
        dial.reset();
    }

    int countTargetNumberPositions() {
        reset();
        var lines = input.lines();
        for (String line : lines) {
            processInstruction(line);
        }
        return targetNumberCount;
    }

    private void processInstruction(String instruction) {
        var direction = instruction.substring(0, 1);
        var steps = Integer.parseInt(instruction.substring(1));

        if (direction.equals("L")) {
            dial.moveLeft(steps);
        } else if (direction.equals("R")) {
            dial.moveRight(steps);
        }

        if (dial.getCurrentValue() == TARGET_NUMBER) {
            targetNumberCount++;
        }
    }

    int countTargetNumberPositionsIncludingInBetween() {
        reset();
        var lines = input.lines();
        for (String line : lines) {
            processInstructionIncludingInBetween(line);
        }
        return targetNumberCount;
    }

    private void processInstructionIncludingInBetween(String line) {
        var direction = line.substring(0, 1);
        var steps = Integer.parseInt(line.substring(1));

        for (int i = 0; i < steps; i++) {
            if (direction.equals("L")) {
                dial.moveLeft(1);
            } else if (direction.equals("R")) {
                dial.moveRight(1);
            }

            if (dial.getCurrentValue() == TARGET_NUMBER) {
                targetNumberCount++;
            }
        }
    }
}
