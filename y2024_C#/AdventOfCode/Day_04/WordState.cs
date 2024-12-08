using AdventOfCode._Utils;

namespace AdventOfCode.Day_04;

public class WordState : IState {
    private const string Word = "XMAS";
    private const int WordLength = 4;
    private readonly int[][] _baseDirections = [[1, 0], [1, 1], [0, 1], [-1, 1], [-1, 0], [-1, -1], [0, -1], [1, -1]];
    private readonly CrossWordMap _crossWordMap;
    private readonly List<Position> _letterPositions;

    public WordState(List<Position> letterPositions, CrossWordMap crossWordMap) {
        _crossWordMap = crossWordMap;
        _letterPositions = letterPositions;
        IntegrityCheck();
    }

    private void IntegrityCheck() {
        if (_letterPositions.Count > WordLength) {
            throw new ArgumentException($"Word length {_letterPositions.Count} too large.");
        }
    }

    public List<IState> NextStates() {
        var nextStates = new List<IState>();
        var currentPosition = _letterPositions[^1];
        var nextRequiredLetter = NextRequiredLetter();
        foreach (var direction in Directions()) {
            var newRow = currentPosition.Row + direction[0];
            var newColumn = currentPosition.Column + direction[1];
            var newPosition = new Position(newRow, newColumn);
            if (_crossWordMap.InsideOfMap(newPosition) && _crossWordMap.Get(newPosition) == nextRequiredLetter) {
                var nextState = NewWordState(newPosition);
                nextStates.Add(nextState);
            }
        }

        return nextStates;
    }

    private char NextRequiredLetter() {
        IntegrityCheck();
        var nextLetterIndex = _letterPositions.Count;
        return Word[nextLetterIndex];
    }

    private int[][] Directions() {
        if (_letterPositions.Count <= 1) {
            return _baseDirections;
        }

        var rowDifference = _letterPositions[^1].Row - _letterPositions[^2].Row;
        var colDifference = _letterPositions[^1].Column - _letterPositions[^2].Column;
        return new int[][] { [rowDifference, colDifference] };
    }

    private WordState NewWordState(Position position) {
        var newLetterPositions = new List<Position>(_letterPositions) { position };
        var nextState = new WordState(newLetterPositions, _crossWordMap);
        return nextState;
    }


    public bool IsGoal() {
        return _letterPositions.Count >= WordLength;
    }

    public override bool Equals(object? obj) {
        if (obj == this) return true;
        if (obj is null || obj.GetType() == GetType()) return false;
        var other = (WordState)obj;
        return _letterPositions.SequenceEqual(other._letterPositions);
    }

    public override int GetHashCode() => HashCode.Combine(_letterPositions);
}