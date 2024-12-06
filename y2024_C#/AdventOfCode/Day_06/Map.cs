namespace AdventOfCode.Day_06;

public class Map(List<string> map) {
    private const char Obstacle = '#';
    private const char StartPosition = '^';

    public Position GetStartPosition() {
        for (var row = 0; row < map.Count; row++) {
            for (var col = 0; col < map[row].Length; col++) {
                if (map[row][col] == StartPosition) {
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

    public bool InsideOfMap(Position position) {
        var rowWithinBounds = 0 <= position.Row && position.Row < map.Count;
        if (rowWithinBounds) {
            var columnWithinBounds = 0 <= position.Column && position.Column < map[position.Row].Length;
            return columnWithinBounds;
        }

        return false;
    }

    public bool IsObstacle(Position position) {
        return map[position.Row][position.Column] == Obstacle;
    }

    public Map WithObstacleAt(Position position) {
        var clonedMap = new List<string>(map);
        var lineToChange = clonedMap[position.Row];
        var changedLine = lineToChange[..position.Column] + Obstacle + lineToChange[(position.Column + 1)..];
        clonedMap[position.Row] = changedLine;
        return new Map(clonedMap);
    }
}

public record Position(int Row, int Column);