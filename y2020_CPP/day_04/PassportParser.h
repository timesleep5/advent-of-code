//
// Created by korbinian on 11/5/26.
//

#pragma once

#include "Passport.h"
#include "../general/Input.h"

using namespace std;

class PassportParser {
    Input input;
    vector<Passport> passports;

    [[nodiscard]] vector<Passport> parse() const;

    [[nodiscard]] map<string, string> static buildData(const string &data);

public:
    PassportParser(const Input &input) : input(input) {
    }

    const vector<Passport> &getPassports();
};
