package day04;

import _general.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    private static final int MAX_ACCESSIBLE_ROLLS_TO_BE_ACCESSIBLE = 3;
    private static final String PAPER_ROLL_ICON = "@";

    private final List<List<String>> cells = new ArrayList<>();

    public Grid(Input input) {
        fillGrid(input);
    }

    private void fillGrid(Input input) {
        input.lines()
                .forEach(l ->
                        cells.add(Arrays.stream(l.split(""))
                                .collect(Collectors.toCollection(ArrayList::new))
                        )
                );
    }

    public int countAccessibleRolls() {
        return getAccessibleRolls().size();
    }

    public int countAccessibleRollsWithRemoval() {
        var accessibleRolls = getAccessibleRolls();
        int accessibleRollsCount = accessibleRolls.size();
        do {
            removeRolls(accessibleRolls);
            accessibleRolls = getAccessibleRolls();
            accessibleRollsCount += accessibleRolls.size();
        } while (!accessibleRolls.isEmpty());
        return accessibleRollsCount;
    }

    private void removeRolls(List<AccessiblePosition> positionsToRemove) {
        for (var position : positionsToRemove) {
            cells.get(position.row()).set(position.col(), ".");
        }
    }

    private List<AccessiblePosition> getAccessibleRolls() {
        var accessibleRolls = new LinkedList<AccessiblePosition>();
        for (int row = 0; row < cells.size(); row++) {
            for (int col = 0; col < cells.get(row).size(); col++) {
                if (!isPaperRoll(row, col)) {
                    continue;
                }
                int adjacentRolls = countAdjacentRolls(row, col);
                if (adjacentRolls <= MAX_ACCESSIBLE_ROLLS_TO_BE_ACCESSIBLE) {
                    accessibleRolls.add(new AccessiblePosition(col, row));
                }
            }
        }
        return accessibleRolls;
    }

    private int countAdjacentRolls(int row, int col) {
        int adjacentRolls = 0;
        for (int adjacentRow = row - 1; adjacentRow <= row + 1; adjacentRow++) {
            for (int adjacentCol = col - 1; adjacentCol <= col + 1; adjacentCol++) {
                if (isAdjacentRoll(adjacentRow, adjacentCol, row, col)) {
                    adjacentRolls++;
                }
            }
        }
        return adjacentRolls;
    }

    private boolean isPaperRoll(int row, int col) {
        return cells.get(row).get(col).equals(PAPER_ROLL_ICON);
    }

    private boolean isAdjacentRoll(int adjacentRow, int adjacentCol, int row, int col) {
        return validAdjacentCell(adjacentRow, adjacentCol, row, col) &&
                isPaperRoll(adjacentRow, adjacentCol);
    }

    private boolean validAdjacentCell(int adjacentRow, int adjacentCol, int row, int col) {
        return withinRowBounds(adjacentRow) && withinColBounds(adjacentCol) && !(adjacentRow == row && adjacentCol == col);
    }

    private boolean withinRowBounds(int row) {
        return row >= 0 && row < cells.size();
    }

    private boolean withinColBounds(int col) {
        return col >= 0 && col < cells.getFirst().size();
    }
}
