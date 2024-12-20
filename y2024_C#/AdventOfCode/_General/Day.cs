namespace AdventofCode._General;

public abstract class Day
{
    private const string InputDirectory = "/home/korbinian/data/code/advent-of-code/y2024_C#/AdventOfCode/_Inputs";
    protected readonly List<string> Input;
    protected int ResultPartOne;
    protected int ResultPartTwo;

    protected Day(string inputFileName)
    {
        var inputPath = Path.Combine(InputDirectory, inputFileName);
        var inputArray = File.ReadAllLines(inputPath)
            .Select(line => line.Trim())
            .ToArray();
        Input = inputArray.ToList();
    }


    public abstract int PartOne();
    public abstract int PartTwo();
}