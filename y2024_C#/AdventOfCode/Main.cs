using AdventOfCode.Day_02;
using AdventofCode._General;

namespace AdventOfCode;

internal abstract class Program {
    public static void Main() {
        Day day = new DayTwo();
        Console.WriteLine($"Part I: {day.PartOne()}");
        Console.WriteLine($"Part II: {day.PartTwo()}");
    }
}