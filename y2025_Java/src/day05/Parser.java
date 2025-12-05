package day05;

import _general.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private List<String> ranges;
    private List<String> values;

    public Parser(Input input) {
        splitLines(input);
    }

    void splitLines(Input input) {
        var lines = input.lines();
        var splitIndex = lines.indexOf("");
        ranges = lines.subList(0, splitIndex);
        values = lines.subList(splitIndex + 1, lines.size());
    }

    List<Range> parseFreshIngredients() {
        return ranges
                .stream()
                .map(s -> {
                    var parts = s.split("-");
                    var start = Long.parseLong(parts[0]);
                    var end = Long.parseLong(parts[1]);
                    return new Range(start, end);
                }).collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    List<Long> parseAvailableIngredients() {
        return values.stream().map(Long::parseLong).toList();
    }
}
