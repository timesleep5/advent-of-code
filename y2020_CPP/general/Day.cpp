//
// Created by korbinian on 10/29/26.
//
#include "Day.h"

#include <iostream>

#include "FileReader.h"

Day::Day(const string &directory) : input(FileReader{directory}.getInput()) {
}

void Day::printName() const {
    cout << name() << endl;
}

void Day::printResultPartOne() {
    string resultPartOne = calculateResultPartOne();
    cout << "Result Part I: " << resultPartOne << endl;
}

void Day::printResultPartTwo() {
    string resultPartTwo = calculateResultPartTwo();
    cout << "Result Part II: " << resultPartTwo << endl;
}
