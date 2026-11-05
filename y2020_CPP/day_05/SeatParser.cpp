//
// Created by korbinian on 11/5/26.
//

#include "SeatParser.h"

#include <complex>

#include "Seat.h"

int SeatParser::parseBinary(const string &binaryString, char oneBit) {
    int result = 0;
    int power = binaryString.length() - 1;
    for (auto c: binaryString) {
        if (c == oneBit) {
            result += pow(2, power);
        }
        power--;
    }
    return result;
}

vector<Seat> SeatParser::parse() const {
    vector<Seat> seats;
    for (auto &line: input.read()) {
        auto rowString = line.substr(0, 7);
        auto columnString = line.substr(7, 3);
        int row = parseBinary(rowString, 'B');
        int column = parseBinary(columnString, 'R');
        seats.emplace_back(row, column);
    }
    return seats;
}


vector<Seat> &SeatParser::getSeats() {
    if (seats.empty()) {
        seats = parse();
    }
    return seats;
}
