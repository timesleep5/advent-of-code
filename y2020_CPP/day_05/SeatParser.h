//
// Created by korbinian on 11/5/26.
//

#pragma once
#include "Seat.h"
#include "../general/Input.h"


class SeatParser {
    Input input;
    vector<Seat> seats;

    [[nodiscard]] vector<Seat> parse() const;

    [[nodiscard]] static int parseBinary(const string &binaryString, char oneBit);

public:
    SeatParser(const Input &input) : input(input) {
    }

    [[nodiscard]] vector<Seat> &getSeats();
};
