package _general;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class FileReader {
    private final String filename;

    FileReader(String filename) {
        this.filename = filename;
    }

    Input getFileInput() {
        Input input;
        try {
            File inputFile = new File(filename);
            input = readFile(inputFile);
        } catch (FileNotFoundException e) {
            input = null;
            System.out.println("Input file not found.");
            System.exit(1);
        }
        return input;
    }

    private Input readFile(File inputFile) throws FileNotFoundException {
        Scanner reader = new Scanner(inputFile);
        StringBuilder inputBuilder = new StringBuilder();
        while (reader.hasNextLine()) {
            inputBuilder.append(reader.nextLine()).append("\n");
        }
        var lines = Arrays.stream(inputBuilder.toString().split("\\R")).toList();
        return new Input(lines);
    }
}
