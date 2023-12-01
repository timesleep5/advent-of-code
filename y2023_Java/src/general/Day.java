package general;

import java.util.Arrays;
import java.util.Objects;

public abstract class Day {
    private final String INPUT_FILENAME = "input.txt";
    protected String[] input;

    public Day() {
        String inputPath = getInputPath();
        input = new FileInput(inputPath).getFileInput();
    }

    protected String getInputPath() {
        return Objects.requireNonNull(getClass().getResource(INPUT_FILENAME)).getPath();
    }
}
