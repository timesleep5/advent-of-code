package _general;

import java.util.Objects;

public abstract class Day {
    private static final String INPUT_FILENAME = "input.txt";
    protected Input input;

    public Day() {
        String inputPath = getInputPath();
        input = new FileReader(inputPath).getFileInput();
    }

    private String getInputPath() {
        return Objects.requireNonNull(getClass().getResource(INPUT_FILENAME)).getPath();
    }

    protected abstract String partOne();

    protected abstract String partTwo();

    protected void printResults() {
        System.out.println("Part One: " + partOne());
        System.out.println("Part Two: " + partTwo());
    }
}
