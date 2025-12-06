package _general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Input(List<String> lines) {
    public List<List<String>> columns(String separator) {
        var columns = new ArrayList<List<String>>();
        var splitLines = lines.stream().map(line -> Arrays.stream(line.split(separator)).toList()).toList();
        for (int col = 0; col < splitLines.getFirst().size(); col++) {
            var column = new ArrayList<String>();
            for (List<String> splitLine : splitLines) {
                column.add(splitLine.get(col));
            }
            columns.add(column);
        }
        return columns;
    }
}
