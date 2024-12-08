using AdventofCode._General;
using AdventOfCode._Utils;

namespace AdventOfCode.Day_04;

public class DayFour : Day {
    private readonly CrossWordMap _crossWordMap;
    private readonly List<IState> _partOneStartStates;
    private readonly List<Mas> _partTwoStartStates;

    public DayFour() : base("input_04.txt") {
        _crossWordMap = new CrossWordMap(Input);
        _partOneStartStates = GetPartOneStartStates();
        _partTwoStartStates = GetPartTwoStartStates();
    }

    private List<IState> GetPartOneStartStates() {
        var startPositions = _crossWordMap.GetAll('X');
        var startStates = startPositions
            .Select(IState (startPosition) => new WordState([startPosition], _crossWordMap))
            .ToList();
        return startStates;
    }

    private List<Mas> GetPartTwoStartStates() {
        var startPositions = _crossWordMap.GetAll('A');
        var startStates = startPositions
            .Select(startPosition => new Mas(startPosition, _crossWordMap))
            .ToList();
        return startStates;
    }

    private void SearchXmaxAppearences() {
        var search = new BreadthFirstSearch();
        var allWords = search.SearchAll(_partOneStartStates);

        ResultPartOne = allWords.Count;
    }

    public override int PartOne() {
        SearchXmaxAppearences();

        return ResultPartOne;
    }

    public override int PartTwo() {
        ValidateMases();

        return ResultPartTwo;
    }

    private void ValidateMases() {
        var masCount = _partTwoStartStates
            .Select(mas => mas.IsXMas() ? 1 : 0)
            .Sum();

        ResultPartTwo = masCount;
    }
}