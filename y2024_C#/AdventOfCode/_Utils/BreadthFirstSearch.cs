namespace AdventOfCode._Utils;

public class BreadthFirstSearch : Search {
    protected override IFrontier GetFrontier(List<IState> startStates) {
        var frontier = new Queue();
        var startPaths = startStates
            .Select(startState => new List<IState> { startState })
            .ToList();
        frontier.EnqueueAll(startPaths);
        return frontier;
    }
}

internal class Queue : IFrontier {
    private const int DefaultCapacity = 10;
    private readonly Queue<List<IState>> _queue;

    internal Queue() {
        _queue = new Queue<List<IState>>(DefaultCapacity);
    }

    internal void EnqueueAll(List<List<IState>> states) {
        foreach (var state in states) {
            _queue.Enqueue(state);
        }
    }

    public void Push(List<IState> path) {
        _queue.Enqueue(path);
    }

    public List<IState> Pop() {
        return _queue.Dequeue();
    }

    public bool IsEmpty() {
        return _queue.Count == 0;
    }
}