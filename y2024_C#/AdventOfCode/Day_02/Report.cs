namespace AdventOfCode.Day_02;

public class Report {
    private const int MinimalStepWidth = 1;
    private const int MaximalStepWidth = 3;
    private const int Increasing = 1;
    private const int Decreasing = -1;
    private readonly List<int> _levels;
    private readonly List<int> _differenceSigns;
    private readonly LevelMonotonicity _levelMonotonicity;

    public Report(List<int> levels) {
        _levels = levels;
        _differenceSigns = DifferenceSigns();
        _levelMonotonicity = GetLevelMonotonicity();
    }

    private LevelMonotonicity GetLevelMonotonicity() {
        var increasingSteps = _differenceSigns.Count(direction => direction == Increasing);
        var decreasingSteps = _differenceSigns.Count(direction => direction == Decreasing);
        return new LevelMonotonicity(increasingSteps, decreasingSteps);
    }

    public bool IsSafe() {
        return IsMonotonous() && HasCorrectStepWidths();
    }

    private bool IsMonotonous() {
        return _levelMonotonicity.IsIncreasing || _levelMonotonicity.IsDecreasing;
    }
    
    private List<int> DifferenceSigns() {
        var directions = new List<int>();
        for (var levelIndex = 0; levelIndex < _levels.Count - 1; levelIndex++) {
            var direction = Math.Sign(_levels[levelIndex + 1] - _levels[levelIndex]);
            directions.Add(direction);
        }

        return directions;
    }

    private bool HasCorrectStepWidths() {
        for (var levelIndex = 0; levelIndex < _levels.Count - 1; levelIndex++) {
            var stepWidth = Math.Abs(_levels[levelIndex + 1] - _levels[levelIndex]);
            if (stepWidth is < MinimalStepWidth or > MaximalStepWidth) {
                return false;
            }
        }

        return true;
    }

    public bool IsSafeWithProblemDampener() {
        if (IsSafe()) {
            return true;
        }

        for (var index = 0; index < _levels.Count; index++) {
            if (IsSafeWithoutIndex(index)) {
                return true;
            }
        }

        return false;
    }

    private bool IsSafeWithoutIndex(int index) {
        var newLevels = new List<int>(_levels);
        newLevels.RemoveRange(index, 1);
        return new Report(newLevels).IsSafe();
    }
}

internal struct LevelMonotonicity {
    internal readonly bool IsIncreasing;
    internal readonly bool IsDecreasing;

    internal LevelMonotonicity(int increasing, int decreasing) {
        IsIncreasing = decreasing == 0;
        IsDecreasing = increasing == 0;
    }
}