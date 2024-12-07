using AdventOfCode.Day_02;
using AdventofCode._General;
using AdventOfCode.Day_01;
using AdventOfCode.Day_03;
using AdventOfCode.Day_04;
using AdventOfCode.Day_05;
using AdventOfCode.Day_06;
using AdventOfCode.Day_07;

namespace AdventOfCode;

internal abstract class Program {
    public static void Main() {
        Day day = new DaySeven();
        Console.WriteLine($"Part I: {day.PartOne()}");
        Console.WriteLine($"Part II: {day.PartTwo()}");
    }
}