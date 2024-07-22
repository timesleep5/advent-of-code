package day_11;

import java.util.ArrayList;
import java.util.LinkedList;

class Universe {
    private static final Character GALAXY = '#';
    private ArrayList<ArrayList<Character>> map;

    Universe(String[] input) {
        createMap(input);
    }

    private void createMap(String[] input) {
        var map = new ArrayList<ArrayList<Character>>();
        for (String line : input) {
            ArrayList<Character> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(c);
            }
            map.add(row);
        }
        this.map = map;
    }

    void expandRow(int rowIndex) {
        var expansionRow = new ArrayList<>(map.get(rowIndex));
        map.add(rowIndex, expansionRow);
    }

    void expandColumn(int columnIndex) {
        var expansionColumn = getColumn(columnIndex);
        for (int row = 0; row < map.size(); row++) {
            var currentRow = map.get(row);
            var currentChar = expansionColumn.get(row);
            currentRow.add(columnIndex, currentChar);
        }
    }

    boolean rowContainsGalaxies(int rowIndex) {
        var row = map.get(rowIndex);
        return row.contains(GALAXY);
    }

    boolean columnContainsGalaxies(int columnIndex) {
        var column = getColumn(columnIndex);
        return column.contains(GALAXY);
    }

    private ArrayList<Character> getColumn(int columnIndex) {
        var column = new ArrayList<Character>();
        for (ArrayList<Character> row : map) {
            column.add(row.get(columnIndex));
        }
        return column;
    }

    LinkedList<Galaxy> getGalaxies() {
        var galaxies = new LinkedList<Galaxy>();
        for (int row = 0; row < map.size(); row++) {
            for (int column = 0; column < map.get(row).size(); column++) {
                var space = map.get(row).get(column);
                if (space.equals(GALAXY)) {
                    galaxies.add(new Galaxy(row, column));
                }
            }
        }
        return galaxies;
    }

    int verticalSize() {
        return map.size();
    }

    int horizontalSize() {
        return map.get(0).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Character> row : map) {
            sb.append(row.toString().replace(", ", ""));
            sb.append("\n");
        }
        return sb.toString();
    }
}
