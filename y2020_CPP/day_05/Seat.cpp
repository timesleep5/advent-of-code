//
// Created by korbinian on 11/5/26.
//

#include "Seat.h"

int Seat::getSeatId() const {
    return row * 8 + column;
}
