package day05;

import _general.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private List<Range> freshIngredients;
    private List<Long> availableIngredients;

    public Inventory(Input input) {
        var parser = new Parser(input);
        freshIngredients = parser.parseFreshIngredients();
        compactFreshIngredients();
        availableIngredients = parser.parseAvailableIngredients();
    }

    private void compactFreshIngredients() {
        var fullyCompacted = false;
        while (!fullyCompacted && freshIngredients.size() > 1) {
            fullyCompacted = true;
            var newFreshIngredients = new ArrayList<Range>();
            Collections.sort(freshIngredients);
            for (int i = 0; i < freshIngredients.size() - 1; i++) {
                var current = freshIngredients.get(i);
                var next = freshIngredients.get(i + 1);
                if (current.mergableWith(next)) {
                    fullyCompacted = false;
                    var mergedRange = current.mergedWith(next);
                    newFreshIngredients.add(mergedRange);
                    i++; //skip the next iteration; next is already represented
                } else {
                    newFreshIngredients.add(current);
                }
            }
            newFreshIngredients.add(freshIngredients.getLast());
            freshIngredients = newFreshIngredients;
        }
    }

    long countFreshAvailableIngredients() {
        return availableIngredients
                .stream()
                .filter(
                        available -> freshIngredients
                                .stream()
                                .anyMatch(freshRange -> freshRange.contains(available))
                ).count();
    }

    long sumUpRangeSizes() {
        return freshIngredients.stream().mapToLong(Range::size).sum();
    }
}
