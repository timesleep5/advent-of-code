#pragma once
#include <fstream>
#include <string>
#include <vector>
using namespace std;

class Day {
public:
    Day(const string &directory);

    virtual ~Day();

    void printResultPartOne();

    void printResultPartTwo();

protected:
    vector<string> input;

    [[nodiscard]] virtual string calculateResultPartOne() = 0;

    [[nodiscard]] virtual string calculateResultPartTwo() = 0;

private:
    const string ROOT_PATH = "/home/korbinian/data/code/advent-of-code/y2020_CPP";
    const string INPUT_FILE = "input.txt";
    string directory;

    [[nodiscard]] vector<string> getInput() const;
};

