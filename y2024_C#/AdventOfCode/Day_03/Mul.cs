namespace AdventOfCode.Day_03;

public abstract class Instruction
{
    public static bool Enabled { set; protected get; } = true;

    public virtual int Execute()
    {
        return 0;
    }
}

public class Mul(int x, int y) : Instruction
{
    public override int Execute()
    {
        return Enabled ? x * y : base.Execute();
    }
}

public class Do : Instruction
{
    public override int Execute()
    {
        Enabled = true;
        return base.Execute();
    }
}

public class Dont : Instruction
{
    public override int Execute()
    {
        Enabled = false;
        return base.Execute();
    }
}