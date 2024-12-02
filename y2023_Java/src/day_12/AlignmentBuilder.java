package day_12;

import java.util.*;
import java.util.stream.Collectors;

class AlignmentBuilder implements TranslationTable {
    private final ArrayList<Integer> damagedSprings;
    private final int totalLength;
    private final int totalOperationals;
    private final HashSet<String> possibleAlignments;

    AlignmentBuilder(Record record) {
        damagedSprings = arrayToList(record.backup());
        totalLength = record.damaged().length();
        totalOperationals = totalLength - sumOfList(damagedSprings);
        possibleAlignments = new HashSet<>();
    }

    private ArrayList<Integer> arrayToList(int[] array) {
        return (ArrayList<Integer>) Arrays.stream(array).boxed().collect(Collectors.toList());
    }

    private int sumOfList(List<Integer> list) {
        return list.stream().mapToInt(i -> i).sum();
    }

    HashSet<String> buildPossibleAlignments() {
        ArrayList<Integer> initialOperationalSprings = initialOperationalSprings();
        buildAlignments(initialOperationalSprings);
        return possibleAlignments;
    }

    /**
     * Creates the smallest possible alignment that separates the damaged springs and has no outer operational springs.
     *
     * @return A list of the form [0, 1, 1, ..., 1, 0]
     */
    private ArrayList<Integer> initialOperationalSprings() {
        ArrayList<Integer> operationalSprings = new ArrayList<>();
        operationalSprings.add(0);
        for (int i = 1; i < damagedSprings.size(); i++) {
            operationalSprings.add(1);
        }
        operationalSprings.add(0);
        return operationalSprings;
    }

    private void buildAlignments(ArrayList<Integer> operationalSprings) {
        if (sumOfList(operationalSprings) == totalOperationals) {
            build(operationalSprings);
            return;
        }
        for (int i = 0; i < operationalSprings.size(); i++) {
            var newOperationalSprings = new ArrayList<>(operationalSprings);
            newOperationalSprings.set(i, newOperationalSprings.get(i) + 1);
            buildAlignments(newOperationalSprings);
        }
    }

    private void build(ArrayList<Integer> operationalSprings) {
        // assumes the alignment is built like this: [o1, d1, o2, d2, ..., on, dn, o(remaining space)]
        assert operationalSprings.size() == damagedSprings.size()+1;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < damagedSprings.size(); i++) {
            String operational = OPERATIONAL.repeat(operationalSprings.get(i));
            String damaged = DAMAGED.repeat(damagedSprings.get(i));
            result.append(operational);
            result.append(damaged);
        }
        result.append(OPERATIONAL.repeat(operationalSprings.get(operationalSprings.size()-1)));
        possibleAlignments.add(result.toString());
    }
}
