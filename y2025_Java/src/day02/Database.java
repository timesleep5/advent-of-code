package day02;

import _general.Input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Database implements Iterable<String> {
    private final List<String> ids;

    public Database(Input input) {
        this.ids = parseIds(input);
    }

    private List<String> parseIds(Input input) {
        var ids = new ArrayList<String>();
        var ranges = input.lines().getFirst().replace("\n", "").split(",");
        for (var range : ranges) {
            var bounds = range.split("-");
            var start = Long.parseLong(bounds[0]);
            var end = Long.parseLong(bounds[1]);
            var idsInRange = generateIdsInRange(start, end);
            ids.addAll(idsInRange);
        }
        return ids;
    }

    private List<String> generateIdsInRange(long start, long end) {
        var idsInRange = new ArrayList<String>();
        for (var i = start; i <= end; i++) {
            idsInRange.add(String.valueOf(i));
        }
        return idsInRange;
    }

    @Override
    public Iterator<String> iterator() {
        return ids.iterator();
    }
}
