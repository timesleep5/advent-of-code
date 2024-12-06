namespace AdventOfCode.Day_06;

public enum Direction {
    Up = 0,
    Right = 1,
    Down = 2,
    Left = 3
}

public class DirectionChanger(Direction startDirection) {
    private readonly List<Instructions> _directionOrder = [
        new Instructions(-1, 0),
        new Instructions(0, 1),
        new Instructions(1, 0),
        new Instructions(0, -1)
    ];

    private int _currentIndex = (int)startDirection;

    public Instructions CurrentDirectionInstruction => _directionOrder[_currentIndex];
    public Direction CurrentDirection => (Direction)_currentIndex;

    public Instructions ChangeDirectionClockwise() {
        _currentIndex = (_currentIndex + 1) % _directionOrder.Count;
        return _directionOrder[_currentIndex];
    }
}

public record Instructions(int RowChange, int ColumnChange);