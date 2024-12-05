using AdventofCode._General;
using AdventOfCode._Utils;

namespace AdventOfCode.Day_04;

public class DayFour : Day {
    private readonly Map _map;
    private readonly List<IState> _partOneStartStates;
    private readonly List<Mas> _partTwoStartStates;

    public DayFour() : base("input_04.txt") {
        _map = new Map(Input);
        _partOneStartStates = GetPartOneStartStates();
        _partTwoStartStates = GetPartTwoStartStates();
    }

    private List<IState> GetPartOneStartStates() {
        var startPositions = _map.GetAll('X');
        var startStates = startPositions
            .Select(IState (startPosition) => new WordState([startPosition], _map))
            .ToList();
        return startStates;
    }

    private List<Mas> GetPartTwoStartStates() {
        var startPositions = _map.GetAll('A');
        var startStates = startPositions
            .Select(startPosition => new Mas(startPosition, _map))
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