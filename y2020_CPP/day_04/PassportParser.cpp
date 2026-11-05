//
// Created by korbinian on 11/5/26.
//

#include "PassportParser.h"
#include "../general/StringManipulation.h"

vector<Passport> PassportParser::parse() const {
    vector<Passport> parsedPassports;
    string data;
    for (string &line: input.read()) {
        if (!line.empty()) {
            StringManipulation::trim(line);
            data += line + " ";
        } else {
            StringManipulation::trim(data);
            parsedPassports.emplace_back(buildData(data));
            data = "";
        }
    }
    StringManipulation::trim(data);
    parsedPassports.emplace_back(buildData(data));
    return parsedPassports;
}

map<string, string> PassportParser::buildData(const string &data) {
    map<string, string> passportData;
    string delimiter = " ";
    auto parts = StringManipulation::split(data, delimiter);
    for (auto &part: parts) {
        auto keyValueDelimiterIndex = part.find(':');
        auto key = part.substr(0, keyValueDelimiterIndex);
        auto value = part.substr(keyValueDelimiterIndex + 1);
        passportData.insert({key, value});
    }
    return passportData;
}

const vector<Passport> &PassportParser::getPassports() {
    if (passports.empty()) {
        passports = parse();
    }
    return passports;
}
