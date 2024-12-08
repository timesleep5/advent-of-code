using AdventofCode._General;

namespace AdventOfCode.Day_08;

public class DayEight : Day {
    private readonly AntinodeDetector _antinodeDetector;
    private readonly ResonantHarmonicsAntinodeDetector _resonantHarmonicsAntinodeDetector;

    public DayEight() : base("input_08.txt") {
        var frequencyMap = new FrequencyMap(Input);
        _antinodeDetector = new AntinodeDetector(frequencyMap);
        _resonantHarmonicsAntinodeDetector = new ResonantHarmonicsAntinodeDetector(frequencyMap);
    }

    public override int PartOne() {
        CountAllValidAntinodePositions();

        return ResultPartOne;
    }

    private void CountAllValidAntinodePositions() {
        var validAntinodePositions = _antinodeDetector.DetectAllAntinodePositions();

        ResultPartOne = validAntinodePositions.Count;
    }

    public override int PartTwo() {
        CountAllValidResonantHarmonicsAntinodePositions();

        return ResultPartTwo;
    }

    private void CountAllValidResonantHarmonicsAntinodePositions() {
        var validAntinodePositions = _resonantHarmonicsAntinodeDetector.DetectAllAntinodePositions();

        ResultPartTwo = validAntinodePositions.Count;
    }
}