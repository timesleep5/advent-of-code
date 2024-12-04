using System.Text;
using System.Text.RegularExpressions;
using AdventofCode._General;

namespace AdventOfCode.Day_03;

public class DayThree : Day
{
    private const string MulPattern = @"mul\(\d{1,3},\d{1,3}\)";
    private const string DoPattern = @"do\(\)";
    private const string DontPattern = @"don't\(\)";
    private readonly string _combinedPattern;
    private readonly string _memoryString;
    private readonly List<Instruction> _instructions;

    public DayThree() : base("input_03.txt")
    {
        _combinedPattern = string.Join("|", [MulPattern, DoPattern, DontPattern]);
        _memoryString = ParseToOneString();
        _instructions = ParseInstructions();
    }

    private string ParseToOneString()
    {
        var sb = new StringBuilder();
        foreach (var memoryString in Input)
        {
            sb.Append(memoryString.Trim());
        }

        return sb.ToString();
    }

    private List<Instruction> ParseInstructions()
    {
        var instructions = new List<Instruction>();
        var matches = Regex.Matches(_memoryString, _combinedPattern);
        foreach (Match match in matches)
        {
            var instruction = InitializeInstruction(match.Value);
            instructions.Add(instruction);
        }

        return instructions;
    }

    private Instruction InitializeInstruction(string instructionString)
    {
        Instruction instruction;
        switch (instructionString[..4])
        {
            case "do()":
                instruction = new Do();
                break;
            case "don'":
                instruction = new Dont();
                break;
            default:
                var integerStrings = instructionString.Replace("mul(", "").Replace(")", "").Split(",");
                instruction = new Mul(int.Parse(integerStrings[0]), int.Parse(integerStrings[1]));
                break;
        }

        return instruction;
    }

    public override int PartOne()
    {
        SumUpMuls();

        return ResultPartOne;
    }

    private void SumUpMuls()
    {
        Instruction.Enabled = true;
        var sum = _instructions.OfType<Mul>().Sum(instruction => instruction.Execute());

        ResultPartOne = sum;
    }

    public override int PartTwo()
    {
        SumUpInstructions();

        return ResultPartTwo;
    }

    private void SumUpInstructions()
    {
        Instruction.Enabled = true;
        var sum = 0;
        foreach (var instruction in _instructions)
        {
            sum += instruction.Execute();
        }

        ResultPartTwo = sum;
    }
}