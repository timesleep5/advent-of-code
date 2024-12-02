package day_12;

import java.util.Arrays;

public class PatternChecker implements TranslationTable {
    private final int[] pattern;
    private final String damagedRecord;

    PatternChecker(Record record) {
        this.pattern = record.backup();
        this.damagedRecord = record.damaged();
    }

    boolean validAlignment(String alignment) {
        if (alignment.length() != damagedRecord.length()) {
            return false;
        }
        int[] alignmentPattern = new int[pattern.length];
        int alignmentPatternIndex = 0;
        int currentOperationals = 0;
        for (int i = 0; i < damagedRecord.length(); i++) {
            char recordChar = damagedRecord.charAt(i);
            char alignmentChar = alignment.charAt(i);

            switch (alignmentChar) {
                case DAMAGED_CHAR -> {
                    if (recordChar == DAMAGED_CHAR || recordChar == WILDCARD_CHAR) {
                        currentOperationals++;
                    } else {
                        return false;
                    }
                }
                case OPERATIONAL_CHAR -> {
                    if (recordChar == OPERATIONAL_CHAR || recordChar == WILDCARD_CHAR) {
                        if (currentOperationals > 0) {
                            alignmentPattern[alignmentPatternIndex] = currentOperationals;
                            alignmentPatternIndex = (++alignmentPatternIndex) % alignmentPattern.length;
                            currentOperationals = 0;
                        }
                    } else {
                        return false;
                    }
                }
                default -> {
                    return false;
                }
            }
        }
        if (currentOperationals > 0) {
            alignmentPattern[alignmentPatternIndex] = currentOperationals;
        }
        return Arrays.equals(pattern, alignmentPattern);
    }
}
