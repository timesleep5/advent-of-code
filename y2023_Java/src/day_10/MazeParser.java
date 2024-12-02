package day_10;

public class MazeParser {
    private final String[] input;

    MazeParser(String[] input) {
        this.input = input;
    }

    char[][] createMap() {
        char[][] map = new char[input.length][];
        for (int line = 0; line < input.length; line++) {
            map[line] = input[line].toCharArray();
        }
        return map;
    }
}
