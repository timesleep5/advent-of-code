#include <iostream>
#include <algorithm>
#include <cstdint>
#include "../general/Day.h"
#include "../general/StringManipulation.h"

class DaySeventeen : public Day {
  public:
    DaySeventeen() : Day("day_17/") {};
    string calculateResultPartOne() override;
    string calculateResultPartTwo() override;
};
