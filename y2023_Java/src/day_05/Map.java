package day_05;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final List<Mapping> mappings;
    Map successor;

    Map() {
        mappings = new ArrayList<>();
    }

    long mapValue(long value) {
        Mapping mapping = findMappingForValue(value);
        long newValue = mapping.mapValue(value);
        if (successor != null) {
            return successor.mapValue(newValue);
        } else {
            return newValue;
        }
    }

    private Mapping findMappingForValue(long value) {
        for (Mapping mapping : mappings) {
            if (mapping.inRange(value)) {
                return mapping;
            }
        }
        return new Mapping(0, 0, Long.MAX_VALUE);
    }

    private Mapping createMapping(String[] mappingValues) {
        long destinationStart = Long.parseLong(mappingValues[0]);
        long sourceStart = Long.parseLong(mappingValues[1]);
        long range = Long.parseLong(mappingValues[2]);
        return new Mapping(destinationStart, sourceStart, range);
    }

    void addMapping(String mappingString) {
        String[] mappingValues = mappingString.strip().split(" ");
        mappings.add(createMapping(mappingValues));
    }
}
