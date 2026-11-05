#include "DayFour.h"

#include "PassportValidator.h"

string DayFour::name() const {
    return "Day 4";
}

string DayFour::calculateResultPartOne() {
    auto passports = passportParser.getPassports();
    auto counter = PassportValidator::countValid(passports);
    return to_string(counter);
}

string DayFour::calculateResultPartTwo() {
    auto passports = passportParser.getPassports();
    auto counter = PassportValidator::countValidStrict(passports);
    return to_string(counter);
}
