namespace AdventOfCode.Day_07;

public abstract class Term {
    public abstract long Evaluate();
}

public class Add(Term left, Term right) : Term {
    public override long Evaluate() {
        return left.Evaluate() + right.Evaluate();
    }
}

public class Multiply(Term left, Term right) : Term {
    public override long Evaluate() {
        return left.Evaluate() * right.Evaluate();
    }
}

public class Concat(Term left, Term right) : Term {
    public override long Evaluate() {
        var leftString = left.Evaluate().ToString();
        var rightString = right.Evaluate().ToString();
        var concatenated = leftString + rightString;
        var result = long.Parse(concatenated);
        return result;
    }
}

public class Number(long value) : Term {
    public override long Evaluate() {
        return value;
    }
}