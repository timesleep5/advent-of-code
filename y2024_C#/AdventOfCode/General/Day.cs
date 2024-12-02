using System.Runtime.CompilerServices;

namespace AdventofCode.General;

public abstract class Day {
    private const string InputDirectory = "/home/korbinian/code/Python/advent-of-code/y2024_C#/AdventOfCode/Inputs";
    protected List<string> Input;
    protected int ResultPartOne;
    protected int ResultPartTwo;

    protected Day(string inputFileName) {
        var inputPath = Path.Combine(InputDirectory, inputFileName);
        var inputArray = File.ReadAllLines(inputPath);
        Input = inputArray.ToList();
    }


    public abstract int PartOne();
    public abstract int PartTwo();
}