using AdventOfCode.Day_02;
using AdventofCode._General;
using AdventOfCode.Day_03;

namespace AdventOfCode;

internal abstract class Program {
    public static void Main() {
        Day day = new DayThree();
        Console.WriteLine($"Part I: {day.PartOne()}");
        Console.WriteLine($"Part II: {day.PartTwo()}");
    }
}