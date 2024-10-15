#include <iostream>
#include <algorithm>
#include <cstdint>
#include "../general/Day.h"
#include "../general/StringManipulation.h"
#include "Password.cpp"

class DayTwo : public Day {
  public:
    DayTwo() : Day("day_02/") {};
    string calculateResultPartOne() override;
    string calculateResultPartTwo() override;
};

string DayTwo::calculateResultPartOne() {
  int counter = 0;
  for (string line: input) {
    if (Password(line).isValid()) {
      counter++;
    }
  }
  return to_string(counter);
}

string DayTwo::calculateResultPartTwo() {
  int counter = 0;
  for (string line: input) {
    if (Password(line).isValid2()) {
      counter++;
    }
  }
  return to_string(counter);
}
