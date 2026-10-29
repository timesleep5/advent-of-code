#pragma once
#include <vector>
#include <string>
using namespace std;

class StringManipulation {
public:
    static void trim(string &toTrim);

    static vector<string> split(string &toSplit, string &delimiter);
};
