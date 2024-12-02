using AdventofCode.General;

namespace AdventOfCode.Day_01;

public class DayOne : Day {
    private readonly List<int> _leftList;
    private readonly List<int> _rightList;
    private int _rightLocationIndex;

    public DayOne() : base("input_01.txt") {
        _leftList = [];
        _rightList = [];
        Parse();
    }

    private void Parse() {
        foreach (var integerStrings in Input
                     .Select(line => line.Trim())
                     .Select(trimmedLine => trimmedLine.Split("   "))) {
            _leftList.Add(int.Parse(integerStrings[0]));
            _rightList.Add(int.Parse(integerStrings[1]));
        }
    }

    public override int PartOne() {
        SortLists();
        SumUpDifferences();

        return ResultPartOne;
    }

    private void SortLists() {
        _leftList.Sort();
        _rightList.Sort();
    }

    private void SumUpDifferences() {
        var sum = _leftList.Select((leftLocation, locationIndex) => Math.Abs(leftLocation - _rightList[locationIndex]))
            .Sum();

        ResultPartOne = sum;
    }


    public override int PartTwo() {
        CalculateSimilarityScore();

        return ResultPartTwo;
    }


    private void CalculateSimilarityScore() {
        var similarityScore = 0;
        _rightLocationIndex = 0;

        foreach (var leftLocation in _leftList) {
            var appearences = CountAppearences(leftLocation);
            similarityScore += leftLocation * appearences;

            if (_rightLocationIndex >= _rightList.Count) {
                break;
            }
        }

        ResultPartTwo = similarityScore;
    }

    private int CountAppearences(int leftLocation) {
        var appearences = 0;
        while (_rightLocationIndex < _rightList.Count && leftLocation >= _rightList[_rightLocationIndex]) {
            if (leftLocation == _rightList[_rightLocationIndex]) {
                appearences++;
            }

            _rightLocationIndex++;
        }

        return appearences;
    }
}