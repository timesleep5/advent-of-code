//
// Created by korbinian on 10/29/26.
//

#include "Input.h"

Input::Input(const vector<string> &lines) : lines(lines) {
}

vector<string> Input::read() const {
    return lines;
}

char Input::at(const int row, const int col) const {
    return lines[row][col];
}
