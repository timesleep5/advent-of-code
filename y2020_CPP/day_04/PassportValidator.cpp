//
// Created by korbinian on 11/5/26.
//

#include "PassportValidator.h"

bool PassportValidator::isValid(const Passport &passport) {
    for (auto &requiredField: requiredFields) {
        if (passport.data.find(requiredField) == passport.data.end()) {
            return false;
        }
    }
    return true;
}

bool PassportValidator::isValidStrict(const Passport &passport) {
    if (!isValid(passport)) {
        return false;
    }
    if (!isValidBirthYear(passport.data.at("byr"))) {
        return false;
    }
    if (!isValidIssueYear(passport.data.at("iyr"))) {
        return false;
    }
    if (!isValidExpirationYear(passport.data.at("eyr"))) {
        return false;
    }
    if (!isValidHeight(passport.data.at("hgt"))) {
        return false;
    }
    if (!isValidHairColor(passport.data.at("hcl"))) {
        return false;
    }
    if (!isValidEyeColor(passport.data.at("ecl"))) {
        return false;
    }
    if (!isValidPassportId(passport.data.at("pid"))) {
        return false;
    }
    return true;
}

bool PassportValidator::isValidBirthYear(const string &birthYear) {
    if (birthYear.length() != 4) {
        return false;
    }
    for (auto c: birthYear) {
        if (!isdigit(c)) {
            return false;
        }
    }
    auto year = stoi(birthYear);
    return 1920 <= year && year <= 2002;
}

bool PassportValidator::isValidIssueYear(const string &issueYear) {
    if (issueYear.length() != 4) {
        return false;
    }
    for (auto c: issueYear) {
        if (!isdigit(c)) {
            return false;
        }
    }
    auto year = stoi(issueYear);
    return 2010 <= year && year <= 2020;
}

bool PassportValidator::isValidExpirationYear(const string &expirationYear) {
    if (expirationYear.length() != 4) {
        return false;
    }
    for (auto c: expirationYear) {
        if (!isdigit(c)) {
            return false;
        }
    }
    auto year = stoi(expirationYear);
    return 2020 <= year && year <= 2030;
}

bool PassportValidator::isValidHeight(const string &height) {
    if (height.length() < 3) {
        return false;
    }
    auto unit = height.substr(height.length() - 2);
    if (unit != "cm" && unit != "in") {
        return false;
    }
    auto value = height.substr(0, height.length() - 2);
    for (auto c: value) {
        if (!isdigit(c)) {
            return false;
        }
    }
    auto num = stoi(value);
    if (unit == "cm") {
        return 150 <= num && num <= 193;
    }
    return 59 <= num && num <= 76;
}

bool PassportValidator::isValidHairColor(const string &hairColor) {
    if (hairColor.length() != 7) {
        return false;
    }
    if (hairColor[0] != '#') {
        return false;
    }
    for (int i = 1; i < hairColor.length(); i++) {
        auto c = hairColor[i];
        if (!isdigit(c) && !(c >= 'a' && c <= 'f')) {
            return false;
        }
    }
    return true;
}

bool PassportValidator::isValidEyeColor(const string &eyeColor) {
    return eyeColor == "amb" || eyeColor == "blu" || eyeColor == "brn" || eyeColor == "gry" || eyeColor == "grn" ||
           eyeColor == "hzl" || eyeColor == "oth";
}

bool PassportValidator::isValidPassportId(const string &passportId) {
    if (passportId.length() != 9) {
        return false;
    }
    for (auto c: passportId) {
        if (!isdigit(c)) {
            return false;
        }
    }
    return true;
}

int PassportValidator::countValid(const vector<Passport> &passports) {
    auto counter = 0;
    for (auto &passport: passports) {
        if (isValid(passport)) {
            counter++;
        }
    }
    return counter;
}

int PassportValidator::countValidStrict(const vector<Passport> &passports) {
    auto counter = 0;
    for (auto &passport: passports) {
        if (isValidStrict(passport)) {
            counter++;
        }
    }
    return counter;
}
