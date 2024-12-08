using AdventofCode._General;
using AdventOfCode._Utils;

namespace AdventOfCode.Day_06;

public class DaySix : Day {
    private readonly ObstacleMap _obstacleMap;
    private readonly Position _startPosition;
    private readonly Direction _startDirection;
    private readonly Walker _walker;

    public DaySix() : base("input_06.txt") {
        _obstacleMap = new ObstacleMap(Input);
        _startPosition = _obstacleMap.GetStartPosition();
        _startDirection = _obstacleMap.GetStartDirection();

        _walker = new Walker(_obstacleMap, _startPosition, _startDirection);
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
            .Select(possibleObstaclePosition => _obstacleMap.WithObstacleAt(possibleObstaclePosition))
            .Select(NewWalker)
            .Count(walker => walker.Loops());

        ResultPartTwo = possibleObstaclesCount;
    }

    private Walker NewWalker(ObstacleMap obstacleMap) {
        return new Walker(obstacleMap, _startPosition, _startDirection);
    }
}