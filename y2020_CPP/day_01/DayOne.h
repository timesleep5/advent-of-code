//
// Created by korbinian on 10/29/26.
//
#pragma once
#include <cstdint>
#include "../general/Day.h"


class DayOne : public Day {
public:
    DayOne() : Day("day_01/") {
        inputAsInts();
        sortInts();
    };

protected:
    [[nodiscard]] string name() const override;

    string calculateResultPartOne() override;

    string calculateResultPartTwo() override;

private:
    const int TARGET_SUM = 2020;
    const int PIVOT = TARGET_SUM / 2;
    vector<int> ints;

    void inputAsInts();

    void sortInts();

    int pivotIndex();

    bool isPivotIndex(uint16_t index);
};
