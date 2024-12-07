using AdventofCode._General;

namespace AdventOfCode.Day_07;

public class DaySeven : Day {
    private readonly List<Equation> _equations;

    public DaySeven() : base("input_07.txt") {
        _equations = new Parser(Input).ParseEquations();
    }

    public override int PartOne() {
        SumUpSolvableEquations();

        return ResultPartOne;
    }

    private void SumUpSolvableEquations(bool withConcat = false) {
        ulong solvableEquationsSum = 0;
        foreach (var equation in _equations) {
            if (equation.Solvable(withConcat)) {
                solvableEquationsSum += (ulong)equation.Result;
            }
        }

        Console.WriteLine($"Result: {solvableEquationsSum} (too big for an integer)");

        ResultPartOne = 0;
    }

    public override int PartTwo() {
        SumUpSolvableEquations(true);

        return ResultPartTwo;
    }
}