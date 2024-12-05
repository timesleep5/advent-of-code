namespace AdventOfCode.Day_05;

public class Parser {
    private readonly List<string> _ruleInput;
    private readonly List<string> _updateInput;

    public Parser(List<string> input) {
        var splitIndex = input.IndexOf("");
        _ruleInput = input[..splitIndex];
        _updateInput = input[(splitIndex + 1)..];
    }

    public List<Rule> GetRules() {
        var rules = (
            from line in _ruleInput
            select line.Split("|")
            into integerStrings
            let before = int.Parse(integerStrings[0])
            let after = int.Parse(integerStrings[1])
            select new Rule(before, after)
        ).ToList();

        return rules;
    }

    public List<Update> GetUpdates() {
        var updates = (
            from line in _updateInput
            select line.Split(",")
            into pageStrings
            let pages = pageStrings
                .Select(int.Parse)
                .ToList()
            select new Update(pages)
        ).ToList();
        
        return updates;
    }
}