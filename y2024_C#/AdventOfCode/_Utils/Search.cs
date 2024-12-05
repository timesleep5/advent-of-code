namespace AdventOfCode._Utils;

public interface IState {
    List<IState> NextStates();
    bool IsGoal();
    int GetHashCode();
    bool Equals(object other);
}

public interface IFrontier {
    List<IState> Pop();
    void Push(List<IState> path);
    bool IsEmpty();
}

public abstract class Search {
    protected abstract IFrontier GetFrontier(List<IState> startStates);

    public List<List<IState>> SearchAll(List<IState> startStates) {
        var foundPaths = new List<List<IState>>();
        var frontier = GetFrontier(startStates);
        var exploredStates = new HashSet<IState>();
        while (!frontier.IsEmpty()) {
            var path = frontier.Pop();
            var currentState = path[^1];
            if (currentState.IsGoal()) {
                foundPaths.Add(path);
                continue;
            }

            foreach (var state in currentState
                         .NextStates()
                         .Where(state => !exploredStates.Contains(state))
                    ) {
                exploredStates.Add(state);
                var newPath = new List<IState>(path) { state };
                frontier.Push(newPath);
            }
        }

        return foundPaths;
    }
}