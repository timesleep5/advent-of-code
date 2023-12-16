package day_05;

public class Mapping {
    private final long sourceStart;
    private final long exclusiveSourceEnd;
    private final long mappingParameter;

    Mapping(long destinationStart, long sourceStart, long range) {
        this.sourceStart = sourceStart;
        this.exclusiveSourceEnd = Math.addExact(sourceStart, range);
        this.mappingParameter = destinationStart-sourceStart;
    }

    boolean inRange(long value) {
        return sourceStart <= value && value < exclusiveSourceEnd;
    }

    long mapValue(long value) {
        return Math.addExact(value, mappingParameter);
    }
}
