namespace AdventOfCode._Utils;

public abstract class Map(List<string> data) {
    protected readonly List<string> Data = data;

    public char Get(Position position) {
        return Data[position.Row][position.Column];
    }

    public List<Position> GetAll(char letter) {
        var positions = new List<Position>();
        for (var row = 0; row < Data.Count; row++) {
            for (var col = 0; col < Data[row].Length; col++) {
                var current = Data[row][col];
                if (current == letter) {
                    positions.Add(new Position(row, col));
                }
            }
        }

        return positions;
    }

    public bool InsideOfMap(Position position) {
        var rowWithinBounds = 0 <= position.Row && position.Row < Data.Count;
        if (rowWithinBounds) {
            var columnWithinBounds = 0 <= position.Column && position.Column < Data[position.Row].Length;
            return columnWithinBounds;
        }

        return false;
    }
}

public record Position(int Row, int Column) {
    public Position Added(Vector vector) {
        var newRow = Row + vector.Rows;
        var newColumn = Column + vector.Columns;
        return new Position(newRow, newColumn);
    }
};

public record Vector(int Rows, int Columns) {
    public static Vector FromPositions(Position tail, Position head) {
        var deltaRow = head.Row - tail.Row;
        var deltaColumn = head.Column - tail.Column;
        return new Vector(deltaRow, deltaColumn);
    }

    public Vector MultipliedBy(int scalar) {
        var newRows = Rows * scalar;
        var newColumns = Columns * scalar;
        return new Vector(newRows, newColumns);
    }
};