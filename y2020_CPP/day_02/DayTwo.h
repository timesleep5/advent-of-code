#pragma once

#include "../general/Day.h"

class DayTwo : public Day {
public:
    DayTwo() : Day("day_02/") {
    }

protected:
    string name() const override;

    string calculateResultPartOne() override;

    string calculateResultPartTwo() override;
};
