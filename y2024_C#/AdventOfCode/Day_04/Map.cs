namespace AdventOfCode.Day_04;

public class Map(List<string> input) {
    public bool WithinBounds(Position position) {
        var rowWithinBounds = 0 <= position.Row && position.Row < input.Count;
        if (rowWithinBounds) {
            var columnWithinBounds = 0 <= position.Column && position.Column < input[position.Row].Length;
            return columnWithinBounds;
        }

        return false;
    }

    public char Get(Position position) {
        return input[position.Row][position.Column];
    }

    public List<Position> GetAll(char letter) {
        var positions = new List<Position>();
        for (var row = 0; row < input.Count; row++) {
            for (var col = 0; col < input[row].Length; col++) {
                var current = input[row][col];
                if (current == letter) {
                    positions.Add(new Position(row, col));
                }
            }
        }

        return positions;
    }
}

public record Position(int Row, int Column);