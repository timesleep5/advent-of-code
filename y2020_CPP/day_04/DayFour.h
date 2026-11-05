#pragma once

#include "PassportParser.h"
#include "../general/Day.h"

class DayFour : public Day {
    PassportParser passportParser;

public:
    DayFour() : Day("day_04/"), passportParser(input) {
    }

protected:
    string name() const override;

    string calculateResultPartOne() override;

    string calculateResultPartTwo() override;
};
