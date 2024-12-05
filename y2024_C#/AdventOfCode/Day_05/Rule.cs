namespace AdventOfCode.Day_05;

public record Rule(int Before, int After) {
    private readonly List<int> _pageOrder = [Before, After];
    private readonly List<int> _currentMatches = [];

    public bool Allows(Update update) {
        return !ConcernedBy(update) || Ok(update);
    }

    public bool ConcernedBy(Update update) {
        MatchPages(update);
        return _currentMatches.Count == 2;
    }

    public bool Ok(Update update) {
        MatchPages(update);
        return _pageOrder.SequenceEqual(_currentMatches);
    }

    private void MatchPages(Update update) {
        _currentMatches.Clear();
        var matchingPages = update.Pages
            .Where(page => page == Before || page == After)
            .ToList();
        _currentMatches.AddRange(matchingPages);
    }
}