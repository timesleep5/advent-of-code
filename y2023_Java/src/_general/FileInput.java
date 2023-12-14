package _general;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput {
    private final String filename;

    FileInput(String filename) {
        this.filename = filename;
    }

    String[] getFileInput() {
        String[] input = new String[0];
        try {
            File inputFile = new File(filename);
            input = readFile(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            System.exit(1);
        }
        return input;
    }

    private String[] readFile(File inputFile) throws FileNotFoundException {
        Scanner reader = new Scanner(inputFile);
        StringBuilder inputBuilder = new StringBuilder();
        while (reader.hasNextLine()) {
            inputBuilder.append(reader.nextLine()).append("\n");
        }
        return inputBuilder.toString().split("\\R");
    }
}
