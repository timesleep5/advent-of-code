using AdventOfCode.Day_01;
using AdventofCode.General;

namespace AdventOfCode;

internal class Program {
    public static void Main() {
        Day day = new DayOne();
        Console.WriteLine($"Part I: {day.PartOne()}");
        Console.WriteLine($"Part II: {day.PartTwo()}");
    }
}