//
// Created by korbinian on 10/29/26.
//
#include "StringManipulation.h"

#include <string>

void StringManipulation::trim(string &toTrim) {
    const auto first = toTrim.find_first_not_of(" \t\n\r\f\v");
    if (first == string::npos) {
        toTrim.clear();
        return;
    }

    const auto last = toTrim.find_last_not_of(" \t\n\r\f\v");
    toTrim = toTrim.substr(first, last - first + 1);
}

vector<string> StringManipulation::split(const string &toSplit, const string &delimiter) {
    vector<string> parts;
    string partialString = toSplit + "";
    string part;
    int splitIndex = partialString.find(delimiter);
    while (splitIndex != -1) {
        part = partialString.substr(0, splitIndex);
        parts.push_back(part);
        partialString = partialString.substr(splitIndex + delimiter.length());
        splitIndex = partialString.find(delimiter);
    }
    parts.push_back(partialString);
    return parts;
}
