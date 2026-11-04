#pragma once

#include "TrajectoryPlanner.h"
#include "../general/Day.h"

class DayThree : public Day {
    TrajectoryPlanner trajectoryPlanner;

public:
    DayThree() : Day("day_03/"), trajectoryPlanner(input) {
    }

protected:
    string name() const override;

    string calculateResultPartOne() override;

    string calculateResultPartTwo() override;
};
