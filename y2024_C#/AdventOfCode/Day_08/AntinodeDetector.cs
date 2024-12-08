using AdventOfCode._Utils;

namespace AdventOfCode.Day_08;

public class AntinodeDetector(FrequencyMap frequencyMap) {
    private readonly List<char> _signalStrengths = GetSignalStrengths();
    protected readonly FrequencyMap FrequencyMap = frequencyMap;

    private static List<char> GetSignalStrengths() {
        var signalStrengths = new List<char>();

        signalStrengths.AddRange(Enumerable.Range('a', 26).Select(c => (char)c));
        signalStrengths.AddRange(Enumerable.Range('A', 26).Select(c => (char)c));
        signalStrengths.AddRange(Enumerable.Range('0', 10).Select(c => (char)c));

        return signalStrengths;
    }

    public HashSet<Position> DetectAllAntinodePositions() {
        var antinodePositions = new HashSet<Position>();
        foreach (var signalStrength in _signalStrengths) {
            var signalPositions = FrequencyMap.GetAll(signalStrength);
            var signalAntinodePositions = ValidAntinodePositionsForAll(signalPositions);
            antinodePositions.UnionWith(signalAntinodePositions);
        }

        return antinodePositions;
    }

    private List<Position> ValidAntinodePositionsForAll(List<Position> signalPositions) {
        var validPositions = new List<Position>();
        if (signalPositions.Count <= 1) {
            return validPositions;
        }

        for (var firstIndex = 0; firstIndex < signalPositions.Count - 1; firstIndex++) {
            var first = signalPositions[firstIndex];
            for (var secondIndex = firstIndex + 1; secondIndex < signalPositions.Count; secondIndex++) {
                var second = signalPositions[secondIndex];
                var antinodePositions = ValidAntinodePositionsForPair(first, second);
                validPositions.AddRange(antinodePositions);
            }
        }

        return validPositions;
    }

    private List<Position> ValidAntinodePositionsForPair(Position tail, Position head) {
        var validAntinodes = new List<Position>();
        var differenceVector = Vector.FromPositions(tail, head);

        var headAntinodes = AntinodesInDirection(head, differenceVector);
        validAntinodes.AddRange(headAntinodes);

        var tailAntinodes = AntinodesInDirection(tail, differenceVector.MultipliedBy(-1));
        validAntinodes.AddRange(tailAntinodes);

        return validAntinodes;
    }

    protected virtual List<Position> AntinodesInDirection(Position antinode, Vector direction) {
        var antinodes = new List<Position>();
        var newAntinode = antinode.Added(direction);
        if (FrequencyMap.InsideOfMap(newAntinode)) {
            antinodes.Add(newAntinode);
        }

        return antinodes;
    }
}

public class ResonantHarmonicsAntinodeDetector(FrequencyMap frequencyMap) : AntinodeDetector(frequencyMap) {
    protected override List<Position> AntinodesInDirection(Position antinode, Vector direction) {
        var antinodes = new List<Position> { antinode };
        var newAntinode = antinode.Added(direction);
        if (FrequencyMap.InsideOfMap(newAntinode)) {
            var childrenAntinodes = AntinodesInDirection(newAntinode, direction);
            antinodes.AddRange(childrenAntinodes);
        }

        return antinodes;
    }
};