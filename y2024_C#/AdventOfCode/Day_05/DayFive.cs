using AdventofCode._General;

namespace AdventOfCode.Day_05;

public class DayFive : Day {
    private readonly List<Rule> _rules;
    private readonly List<Update> _updates;


    public DayFive() : base("input_05.txt") {
        var parser = new Parser(Input);
        _rules = parser.GetRules();
        _updates = parser.GetUpdates();
    }

    public override int PartOne() {
        SumUpCompliantPages();

        return ResultPartOne;
    }

    private void SumUpCompliantPages() {
        var sum = _updates
            .Select(update => _rules
                    .All(rule => rule.Allows(update))
                    ? update.MiddlePage
                    : 0
            ).Sum();

        ResultPartOne = sum;
    }

    public override int PartTwo() {
        SortWrongUpdatesAndSumUpPages();

        return ResultPartTwo;
    }

    private void SortWrongUpdatesAndSumUpPages() {
        var sum = 0;
        foreach (var update in _updates) {
            var gotSorted = SortIfNecessary(update);
            if (gotSorted) {
                sum += update.MiddlePage;
            }
        }

        ResultPartTwo = sum;
    }

    private bool SortIfNecessary(Update update) {
        var concernedRules = update.GetConcernedRules(_rules);
        var compliant = update.CompliantWith(concernedRules);

        if (compliant) {
            return false;
        }

        update.Sort(concernedRules);
        return true;
    }
}