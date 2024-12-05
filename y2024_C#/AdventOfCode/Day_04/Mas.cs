using System.Text;

namespace AdventOfCode.Day_04;

public class Mas {
    private static readonly int[][] Corners = [[-1, -1], [-1, 1], [1, -1], [1, 1]];
    private static readonly string[] CornerPossibilities = ["SSMM", "MMSS", "MSMS", "SMSM"];
    private readonly Position _midpoint;
    private readonly Map _map;
    private readonly string _cornerString;

    public Mas(Position midpoint, Map map) {
        _midpoint = midpoint;
        _map = map;
        _cornerString = GetCornerString();
    }

    private string GetCornerString() {
        var sb = new StringBuilder();
        foreach (var corner in Corners) {
            var newRow = _midpoint.Row + corner[0];
            var newColumn = _midpoint.Column + corner[1];
            var newPosition = new Position(newRow, newColumn);
            if (_map.WithinBounds(newPosition)) {
                var cornerLetter = _map.Get(newPosition);
                sb.Append(cornerLetter);
            }
        }

        return sb.ToString();
    }

    public bool IsXMas() {
        return CornerPossibilities.Contains(_cornerString);
    }
}