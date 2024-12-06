using AdventofCode._General;

namespace AdventOfCode.Day_06;

public class DaySix : Day {
    private readonly Map _map;
    private readonly Position _startPosition;
    private readonly Direction _startDirection;
    private readonly Walker _walker;

    public DaySix() : base("input_06.txt") {
        _map = new Map(Input);
        _startPosition = _map.GetStartPosition();
        _startDirection = _map.GetStartDirection();

        _walker = new Walker(_map, _startPosition, _startDirection);
        _walker.Walk();
    }

    public override int PartOne() {
        WalkAndCountSteps();

        return ResultPartOne;
    }

    private void WalkAndCountSteps() {
        var steps = _walker.UniqueVisitedPositions;

        ResultPartOne = steps.Count;
    }

    public override int PartTwo() {
        CountPossibleObstacles();

        return ResultPartTwo;
    }

    private void CountPossibleObstacles() {
        var possibleObstaclePositions = _walker.UniqueVisitedPositions;
        var possibleObstaclesCount = possibleObstaclePositions
            .Select(possibleObstaclePosition => _map.WithObstacleAt(possibleObstaclePosition))
            .Select(NewWalker)
            .Count(walker => walker.Loops());

        ResultPartTwo = possibleObstaclesCount;
    }

    private Walker NewWalker(Map map) {
        return new Walker(map, _startPosition, _startDirection);
    }
}