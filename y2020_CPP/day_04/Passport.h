//
// Created by korbinian on 11/4/26.
//

#pragma once
#include <map>
#include <string>

using namespace std;


struct Passport {

    map<string, string> data;

    explicit Passport(map<string, string> data) : data(std::move(data)) {
    }
};
