package day_11;

import java.util.HashSet;
import java.util.LinkedList;

class UniverseManager {
    private final Universe universe;
    private final HashSet<GalaxyPair> galaxyPairs;
    private int shortestPathSum;

    UniverseManager(String[] input) {
        universe = new Universe(input);
        galaxyPairs = new HashSet<>();
    }

    int getShortestPathSum() {
        expand();
        collectPairs();
        calculateShortestPaths();
        return shortestPathSum;
    }

    private void expand() {
        expandRows();
        expandColumns();
    }


    private void expandRows() {
        var rowsToExpand = searchRowsToExpand();
        int shiftOfPreviousExpansions = 0;
        for (int rowToExpand : rowsToExpand) {
            int actualRowToExpand = rowToExpand + shiftOfPreviousExpansions;
            universe.expandRow(actualRowToExpand);
            shiftOfPreviousExpansions++;
        }
    }

    private LinkedList<Integer> searchRowsToExpand() {
        var rowsToExpand = new LinkedList<Integer>();
        for (int row = 0; row < universe.verticalSize(); row++) {
            if (!universe.rowContainsGalaxies(row)) {
                rowsToExpand.add(row);
            }
        }
        return rowsToExpand;
    }

    private void expandColumns() {
        var columnsToExpand = searchColumnsToExpand();
        int shiftOfPreviousExpansions = 0;
        for (int columnToExpand : columnsToExpand) {
            int actualColumnToExpand = columnToExpand + shiftOfPreviousExpansions;
            universe.expandColumn(actualColumnToExpand);
            shiftOfPreviousExpansions++;
        }
    }

    private LinkedList<Integer> searchColumnsToExpand() {
        var columnsToExpand = new LinkedList<Integer>();
        for (int column = 0; column < universe.horizontalSize(); column++) {
            if (!universe.columnContainsGalaxies(column)) {
                columnsToExpand.add(column);
            }
        }
        return columnsToExpand;
    }

    private void collectPairs() {
        var galaxies = universe.getGalaxies();
        for (var first : galaxies) {
            for (var second : galaxies) {
                if (!first.equals(second)) {
                    galaxyPairs.add(new GalaxyPair(first, second));
                }
            }
        }
        System.out.println(galaxyPairs.size()); // should be 96580

    }

    private void calculateShortestPaths() {
        for (GalaxyPair galaxyPair : galaxyPairs) {
            shortestPathSum += galaxyPair.shortestPath();
        }
    }

    @Override
    public String toString() {
        return universe.toString();
    }
}


