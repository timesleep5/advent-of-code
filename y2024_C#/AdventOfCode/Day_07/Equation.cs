using AdventOfCode._Utils;

namespace AdventOfCode.Day_07;

public class Equation(long result, List<Term> terms) {
    public long Result { get; } = result;

    public bool Solvable(bool withConcat) {
        var paths = SearchAllInitializations(withConcat);
        var initializedEquations = ExtractInitializedEquations(paths);
        var solvable = ContainsSolvableTerms(initializedEquations);
        return solvable;
    }

    private List<List<IState>> SearchAllInitializations(bool withConcat) {
        var startState = new EquationState(terms, withConcat);
        var startStates = new List<IState> { startState };
        var search = new BreadthFirstSearch();
        var paths = search.SearchAll(startStates);
        return paths;
    }

    private List<EquationState> ExtractInitializedEquations(List<List<IState>> paths) {
        var initializedEquations = paths
            .Select(path => (EquationState)path.Last())
            .ToList();
        return initializedEquations;
    }

    private bool ContainsSolvableTerms(List<EquationState> equations) {
        return equations
            .Select(equation => equation.Evaluate())
            .Any(equationResult => equationResult == Result);
    }
}

internal class EquationState(List<Term> terms, bool withConcat) : IState {
    public List<IState> NextStates() {
        var left = terms[0];
        var right = terms[1];
        var add = new Add(left, right);
        var multiply = new Multiply(left, right);
        var combinedTerms = new List<Term> { add, multiply };
        AddConcatIfNecessary(combinedTerms, left, right);
        var nextStates = BuildNextStates(combinedTerms);
        return nextStates;
    }

    private void AddConcatIfNecessary(List<Term> combinedTerms, Term left, Term right) {
        if (withConcat) {
            var concat = new Concat(left, right);
            combinedTerms.Add(concat);
        }
    }

    private List<IState> BuildNextStates(List<Term> combinedTerms) {
        var nextStates = combinedTerms
            .Select(IState (combinedTerm) => {
                    var newTerms = new List<Term> { combinedTerm };
                    newTerms.AddRange(terms.Skip(2));
                    return new EquationState(newTerms, withConcat);
                }
            ).ToList();
        return nextStates;
    }

    public bool IsGoal() {
        return terms.Count == 1;
    }

    internal long Evaluate() {
        return terms[0].Evaluate();
    }
}