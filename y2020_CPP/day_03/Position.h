//
// Created by korbinian on 10/29/26.
//

#pragma once
#include <ostream>
using namespace std;

struct Position {
    int x;
    int y;

    friend ostream &operator<<(ostream &os, const Position &position) {
        os << "Position{x=" << position.x << ", y=" << position.y << "}";
        return os;
    }
};
