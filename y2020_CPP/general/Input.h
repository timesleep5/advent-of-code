//
// Created by korbinian on 10/29/26.
//
#pragma once
#include <string>
#include <vector>
using namespace std;


class Input {
    const vector<string> lines;

public:
    Input(const vector<string> &lines);
    vector<string> read() const;
};
