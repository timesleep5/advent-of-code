package general;

public abstract class Day {
    private String[] input;

    public Day(String filename) {
        input = new FileInput(filename).getFileInput();
    }
}
