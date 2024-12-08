using AdventOfCode._Utils;

namespace AdventOfCode.Day_06;

public class ObstacleMap : Map {
    private const char Obstacle = '#';
    private const char StartPosition = '^';

    public ObstacleMap(List<string> data) : base(data) {
    }

    public Position GetStartPosition() {
        for (var row = 0; row < Data.Count; row++) {
            for (var col = 0; col < Data[row].Length; col++) {
                if (Data[row][col] == StartPosition) {
                    return new Position(row, col);
                }
            }
        }

        throw new Exception("No start position found");
    }

    public Direction GetStartDirection() {
        return Direction.Up;
    }

    public bool ValidStep(Position position) {
        return InsideOfMap(position) && IsObstacle(position);
    }

    public bool IsObstacle(Position position) {
        return Data[position.Row][position.Column] == Obstacle;
    }

    public ObstacleMap WithObstacleAt(Position position) {
        var clonedMap = new List<string>(Data);
        var lineToChange = clonedMap[position.Row];
        var changedLine = lineToChange[..position.Column] + Obstacle + lineToChange[(position.Column + 1)..];
        clonedMap[position.Row] = changedLine;
        return new ObstacleMap(clonedMap);
    }
}