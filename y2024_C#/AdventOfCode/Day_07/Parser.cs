namespace AdventOfCode.Day_07;

public class Parser(List<string> input) {
    public List<Equation> ParseEquations() {
        return (
            from parts in input.Select(line => line.Split(':'))
            let result = long.Parse(parts[0])
            let terms = parts[1]
                .Trim()
                .Split(' ')
                .Select(long.Parse)
                .Select(integer => (Term)new Number(integer))
                .ToList()
            select new Equation(result, terms)
        ).ToList();
    }
}