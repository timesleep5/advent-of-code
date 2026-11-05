//
// Created by korbinian on 11/5/26.
//

#pragma once


class Seat {
    int row;
    int column;

public:
    Seat(int row, int column) : row(row), column(column) {
    }

    [[nodiscard]] int getSeatId() const;
};
