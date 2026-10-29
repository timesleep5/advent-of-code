#pragma once
#include <string>

#include "Input.h"
using namespace std;

class Day {
public:
    Day(const string &directory);

    void printName() const;

    void printResultPartOne();

    void printResultPartTwo();

protected:
    const Input input;

    [[nodiscard]] virtual string name() const = 0;

    [[nodiscard]] virtual string calculateResultPartOne() = 0;

    [[nodiscard]] virtual string calculateResultPartTwo() = 0;
};
