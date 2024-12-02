package day_12;

import java.util.ArrayList;
import java.util.Arrays;

public class RecordsParser {
    private final String[] input;
    RecordsParser(String[] input) {
        this.input = input;
    }

    ArrayList<Record> parse() {
        ArrayList<Record> records = new ArrayList<>();
        for (String line : input) {
            String[] parts = line.split(" ");
            String damaged = parts[0];
            String[] backupStrings = parts[1].split(",");
            int[] backup = Arrays.stream(backupStrings).mapToInt(Integer::parseInt).toArray();
            records.add(new Record(damaged, backup));
        }
        return records;
    }
}
