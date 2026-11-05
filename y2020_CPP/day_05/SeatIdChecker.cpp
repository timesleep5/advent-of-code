//
// Created by korbinian on 11/5/26.
//

#include "SeatIdChecker.h"

#include <algorithm>
#include <stdexcept>


int SeatIdChecker::findHighestId(vector<Seat> &seats) {
    auto maxId = 0;
    for (Seat &seat: seats) {
        if (auto id = seat.getSeatId(); id > maxId) {
            maxId = id;
        }
    }
    return maxId;
}


int SeatIdChecker::findOwnId(vector<Seat> &seats) {
    vector<int> ids;
    for (auto &seat: seats) {
        ids.push_back(seat.getSeatId());
    }
    ranges::sort(ids);
    return findGapInSorted(ids);
}

int SeatIdChecker::findGapInSorted(vector<int> ids) {
    int previousId = ids.at(0) - 1;
    for (auto currentId: ids) {
        if (currentId - previousId == 2) {
            return currentId - 1;
        }
        previousId = currentId;
    }
    throw runtime_error("no gap of 2 found");
}
