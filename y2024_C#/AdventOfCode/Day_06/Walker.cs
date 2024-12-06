namespace AdventOfCode.Day_06;

public class Walker {
    private readonly Map _map;
    private readonly DirectionChanger _directionChanger;
    private readonly List<Position> _visitedPositions;
    private readonly HashSet<State> _pastStates;
    private Position _currentPosition;
    private Instructions _currentDirectionInstruction;
    private bool _inLoop;

    public HashSet<Position> UniqueVisitedPositions => [.._visitedPositions];

    public Walker(Map map, Position startPosition, Direction startDirection) {
        _map = map;
        _visitedPositions = [startPosition];
        _currentPosition = startPosition;
        _pastStates = [new State(startPosition, startDirection)];
        _directionChanger = new DirectionChanger(startDirection);
        _currentDirectionInstruction = _directionChanger.CurrentDirectionInstruction;
    }

    public void Walk() {
        while (true) {
            var nextPosition = NextPosition();
            if (_map.InsideOfMap(nextPosition)) {
                TakeNextStep(nextPosition);
            }
            else {
                break;
            }
        }
    }

    private void TakeNextStep(Position nextPosition) {
        while (_map.IsObstacle(nextPosition)) {
            TurnRight();
            nextPosition = NextPosition();
        }

        _currentPosition = nextPosition;
        _visitedPositions.Add(_currentPosition);
    }

    private void TurnRight() {
        _currentDirectionInstruction = _directionChanger.ChangeDirectionClockwise();
    }

    private Position NextPosition() {
        var newRow = _currentPosition.Row + _currentDirectionInstruction.RowChange;
        var newColumn = _currentPosition.Column + _currentDirectionInstruction.ColumnChange;
        return new Position(newRow, newColumn);
    }

    public bool Loops() {
        while (true) {
            var nextPosition = NextPosition();
            if (_map.InsideOfMap(nextPosition)) {
                TakeNextStep(nextPosition);
                UpdateInLoop();
                if (_inLoop) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
    }

    private void UpdateInLoop() {
        var currentState = new State(_currentPosition, _directionChanger.CurrentDirection);
        if (_pastStates.Contains(currentState)) {
            _inLoop = true;
        }

        _pastStates.Add(currentState);
    }
}

internal record State(Position Position, Direction Direction);