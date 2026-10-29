//
// Created by korbinian on 10/29/26.
//
#include "Day.h"

#include <iostream>

Day::Day(const string &directory) {
    this->directory = directory;
    input = getInput();
}

Day::~Day() = default;

vector<string> Day::getInput() const {
    string path = ROOT_PATH + directory + INPUT_FILE;
    ifstream file(path);
    string line;
    vector<string> lines;
    while (getline(file, line)) {
        cout << line << endl;
        lines.push_back(line);
    }

    return lines;
}

void Day::printResultPartOne() {
    string resultPartOne = calculateResultPartOne();
    cout << "Result Part I: " << resultPartOne << endl;
}

void Day::printResultPartTwo() {
    string resultPartTwo = calculateResultPartTwo();
    cout << "Result Part II: " << resultPartTwo << endl;
}
