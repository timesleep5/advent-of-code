package _general;

import java.util.Objects;

public abstract class Day {
    private static final String INPUT_FILENAME = "input.txt";
    protected String[] input;
    protected int resultPartOne;
    protected int resultPartTwo;

    public Day() {
        String inputPath = getInputPath();
        input = new FileInput(inputPath).getFileInput();
    }

    protected String getInputPath() {
        return Objects.requireNonNull(getClass().getResource(INPUT_FILENAME)).getPath();
    }
    public abstract int partOne();
    public abstract int partTwo();
}
