#include "DayTwo.h"

#include "Password.h"

string DayTwo::name() const {
    return "Day 2";
}

string DayTwo::calculateResultPartOne() {
    int counter = 0;
    for (string line: input.read()) {
        if (Password(line).isValid()) {
            counter++;
        }
    }
    return to_string(counter);
}

string DayTwo::calculateResultPartTwo() {
    int counter = 0;
    for (string line: input.read()) {
        if (Password(line).isValid2()) {
            counter++;
        }
    }
    return to_string(counter);
}
