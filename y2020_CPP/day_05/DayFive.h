#pragma once

#include "SeatParser.h"
#include "../general/Day.h"

class DayFive : public Day {
    SeatParser seatParser;

public:
    DayFive() : Day("day_05/"), seatParser(input) {
    }

protected:
    string name() const override;

    string calculateResultPartOne() override;

    string calculateResultPartTwo() override;
};
