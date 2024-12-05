namespace AdventOfCode.Day_05;

public record Update(List<int> Pages) {
    public int MiddlePage => Pages[Pages.Count / 2];

    public List<Rule> GetConcernedRules(List<Rule> rules) {
        var concernedRules = rules
            .Where(rule => rule.ConcernedBy(this))
            .ToList();
        return concernedRules;
    }

    public bool CompliantWith(List<Rule> rules) {
        var compliant = rules.All(rule => rule.Ok(this));
        return compliant;
    }

    public void Sort(List<Rule> rules) {
        int[] afterRulesAppearances = new int[Pages.Count];
        Array.Fill(afterRulesAppearances, 0);
        foreach (var rule in rules) {
            var indexToIncrease = Pages.IndexOf(rule.After);
            afterRulesAppearances[indexToIncrease]++;
        }

        SortIntoPages(afterRulesAppearances);
    }

    private void SortIntoPages(int[] afterRulesAppearances) {
        var newPages = new int[Pages.Count];
        for (var oldIndex = 0; oldIndex < afterRulesAppearances.Length; oldIndex++) {
            var newIndex = afterRulesAppearances[oldIndex];
            newPages[newIndex] = Pages[oldIndex];
        }

        Pages.Clear();
        Pages.AddRange(newPages.ToList());
    }
};