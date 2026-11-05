//
// Created by korbinian on 11/5/26.
//

#pragma once
#include <vector>

#include "Seat.h"
using namespace std;


class SeatIdChecker {
    [[nodiscard]] static int findGapInSorted(vector<int> ids);

public:
    [[nodiscard]] static int findHighestId(vector<Seat> &seats);

    [[nodiscard]] static int findOwnId(vector<Seat> &seats);
};
