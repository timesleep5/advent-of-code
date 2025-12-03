package day02;

public class IdChecker {
    public static boolean isInvalid(String id) {
        return hasLeadinZero(id) || hasPatternTwice(id);
    }

    private static boolean hasLeadinZero(String id) {
        return id.charAt(0) == '0';
    }

    private static boolean hasPatternTwice(String id) {
        if (id.length() % 2 != 0) {
            return false;
        }
        var halfLength = id.length() / 2;
        var firstHalf = id.substring(0, halfLength);
        var secondHalf = id.substring(halfLength);
        return firstHalf.equals(secondHalf);
    }

    public static boolean isInvalidStrict(String id) {
        return hasLeadinZero(id) || hasRepeatingPattern(id);
    }

    private static boolean hasRepeatingPattern(String id) {
        StringBuilder currentPattern = new StringBuilder();
        for (int i = 0; i < id.length() / 2; i++) {
            currentPattern.append(id.charAt(i));
            var patternLength = currentPattern.length();
            if (id.length() % patternLength != 0) {
                continue;
            }
            if (patternFitsId(id, currentPattern.toString())) {
                return true;
            }
        }
        return false;
    }

    private static boolean patternFitsId(String id, String pattern) {
        var repetitions = id.length() / pattern.length();
        return pattern.repeat(repetitions).equals(id);
    }
}
