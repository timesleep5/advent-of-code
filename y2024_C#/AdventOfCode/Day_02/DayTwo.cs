using AdventofCode._General;

namespace AdventOfCode.Day_02;

public class DayTwo : Day {
    private readonly List<Report> _reports;

    public DayTwo() : base("input_02.txt") {
        _reports = [];
        Parse();
    }

    private void Parse() {
        foreach (var levels in Input
                     .Select(line => line.Split(" "))
                     .Select(integerStrings => integerStrings
                         .Select(int.Parse)
                         .ToList())
                ) {
            var report = new Report(levels);
            _reports.Add(report);
        }
    }

    public override int PartOne() {
        CountSafeReports();

        return ResultPartOne;
    }

    private void CountSafeReports() {
        var safeReports = _reports.Count(report => report.IsSafe());
        ResultPartOne = safeReports;
    }

    public override int PartTwo() {
        CountSafeWithProblemDampenerReports();

        return ResultPartTwo;
    }

    private void CountSafeWithProblemDampenerReports() {
        var safeReports = _reports.Count(report => report.IsSafeWithProblemDampener());
        ResultPartTwo = safeReports;
    }
}