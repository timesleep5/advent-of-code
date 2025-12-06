package day06;

import _general.Input;

import java.util.ArrayList;
import java.util.List;

class Parser {
    private final Input input;

    Parser(Input input) {
        this.input = input;
    }

    List<Equation> parseEquations() {
        var columns = getColumns();
        return columns
                .stream()
                .map(this::parseEquation)
                .toList();
    }

    private List<List<String>> getColumns() {
        var splitIndices = getSplitIndices();
        return splitByIndices(splitIndices);
    }

    private List<List<String>> splitByIndices(List<Integer> splitIndices) {
        var oldLines = input.lines();
        var newColumns = new ArrayList<List<String>>();
        for (int splitIndexIndex = 0; splitIndexIndex < splitIndices.size() - 1; splitIndexIndex++) {
            var newColumn = new ArrayList<String>();
            for (var oldLine : oldLines) {
                var start = splitIndices.get(splitIndexIndex);
                var end = splitIndices.get(splitIndexIndex + 1) - 1; // because of space separator that is skipped
                var colElement = oldLine.substring(start, end);
                newColumn.add(colElement);
            }
            newColumns.add(newColumn);
        }
        return newColumns;
    }

    private List<Integer> getSplitIndices() {
        var line = input.lines().getLast();
        var splitIndices = new ArrayList<Integer>();
        for (var index = 0; index < line.length(); index++) {
            var symbol = line.charAt(index);
            if (symbol == '+' || symbol == '*') {
                splitIndices.add(index);
            }
        }
        splitIndices.add(line.length() + 1); // to keep the pattern
        return splitIndices;
    }

    private Equation parseEquation(List<String> column) {
        var elements = column
                .subList(0, column.size() - 1)
                .stream()
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .boxed().toList();
        var operation = column.getLast();
        return buildEquation(elements, operation);
    }

    List<Equation> parseRightToLeftEquations() {
        var columns = getColumns();
        return columns
                .stream()
                .map(this::parseRightToLeftEquation)
                .toList();
    }

    private Equation parseRightToLeftEquation(List<String> column) {
        var elements = getRightToLeftElements(column.subList(0, column.size() - 1));
        var operation = column.getLast();
        return buildEquation(elements, operation);
    }

    private List<Long> getRightToLeftElements(List<String> elements) {
        var rightToLeftElements = new ArrayList<Long>();
        var longestNumber = elements.stream().mapToLong(String::length).max().getAsLong();
        for (int i = 0; i < longestNumber; i++) {
            var num = new StringBuilder();
            for (var element : elements) {
                if (i < element.length() && element.charAt(i) != ' ') {
                    num.append(element.charAt(i));
                }
            }
            rightToLeftElements.add(Long.parseLong(num.toString()));
        }
        return rightToLeftElements;
    }

    private Equation buildEquation(List<Long> elements, String operation) {
        return switch (operation.trim()) {
            case "+" -> new Addition(elements);
            case "*" -> new Multiplication(elements);
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
