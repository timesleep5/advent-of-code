package day_12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class RecordsManager {
    private final ArrayList<Record> records;
    private PatternChecker currentPatternChecker;

    public RecordsManager(String[] input) {
        this.records = new RecordsParser(input).parse();
    }

    int numberOfValidAlignments() {
        int totalAlignments = 0;
        for (Record record : records) {
            currentPatternChecker = new PatternChecker(record);
            var possibleAlignments = createAllPossibleAlignments(record);
            var validAlignments = filterPossibleAlignments(possibleAlignments);
            int numberOfValidAlignments = validAlignments.size();
            totalAlignments += numberOfValidAlignments;
        }
        return totalAlignments;
    }

    private HashSet<String> createAllPossibleAlignments(Record record) {
        var alignmentBuilder = new AlignmentBuilder(record);
        return alignmentBuilder.buildPossibleAlignments();
    }


    private LinkedList<String> filterPossibleAlignments(HashSet<String> possibleAlignments) {
        return possibleAlignments
                .stream()
                .filter(a -> currentPatternChecker.validAlignment(a))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
