package day05;

class Range implements Comparable<Range> {
    long start;
    long end;

    Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    boolean mergableWith(Range range) {
        return !(end + 1 < range.start || range.end + 1 < start);
    }


    Range mergedWith(Range range) {
        if (!mergableWith(range)) {
            throw new RuntimeException("Range merging not possible");
        }

        var newStart = Math.min(start, range.start);
        var newEnd = Math.max(end, range.end);
        return new Range(newStart, newEnd);
    }

    boolean contains(long value) {
        return start <= value && value <= end;
    }

    long size() {
        return end - start + 1;
    }

    @Override
    public int compareTo(Range o) {
        return Long.compare(start, o.start);
    }
}
