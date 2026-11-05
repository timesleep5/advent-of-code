#include "DayFive.h"

#include "SeatIdChecker.h"

string DayFive::name() const {
    return "Day 5";
}

string DayFive::calculateResultPartOne() {
    auto seats = seatParser.getSeats();
    auto maxId = SeatIdChecker::findHighestId(seats);
    return to_string(maxId);
}

string DayFive::calculateResultPartTwo() {
    auto seats = seatParser.getSeats();
    auto ownId = SeatIdChecker::findOwnId(seats);
    return to_string(ownId);
}
