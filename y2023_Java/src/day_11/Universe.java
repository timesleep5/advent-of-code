package day_11;

import java.util.ArrayList;
import java.util.LinkedList;

class Universe {
    private static final Character GALAXY = '#';
    private final ArrayList<ArrayList<Character>> map;
    private final ArrayList<Integer> expandedRows;
    private final ArrayList<Integer> expandedColumns;

    Universe(String[] input) {
        map = new ArrayList<>();
        expandedRows = new ArrayList<>();
        expandedColumns = new ArrayList<>();
        createMap(input);
        findExpandedRows();
        findExpandedColumns();
    }

    private void createMap(String[] input) {
        for (String line : input) {
            ArrayList<Character> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(c);
            }
            map.add(row);
        }
    }

    private void findExpandedRows() {
        for (int row = 0; row < map.size(); row++) {
            if (!rowContainsGalaxies(row)) {
                expandedRows.add(row);
            }
        }
    }

    private void findExpandedColumns() {
        for (int column = 0; column < map.get(0).size(); column++) {
            if (!columnContainsGalaxies(column)) {
                expandedColumns.add(column);
            }
        }
    }

    int expansionsBetween(GalaxyPair pair) {
        int minX = Math.min(pair.first().x(), pair.second().x());
        int maxX = Math.max(pair.first().x(), pair.second().x());
        int minY = Math.min(pair.first().y(), pair.second().y());
        int maxY = Math.max(pair.first().y(), pair.second().y());
        int expansions = 0;
        for (int expandedRow : expandedRows) {
            if (expandedRow >= minY && expandedRow <= maxY) {
                expansions++;
            }
        }
        for (int expandedColumn : expandedColumns) {
            if (expandedColumn >= minX && expandedColumn <= maxX) {
                expansions++;
            }
        }
        return expansions;
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
                    galaxies.add(new Galaxy(column, row));
                }
            }
        }
        return galaxies;
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
