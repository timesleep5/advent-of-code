//
// Created by korbinian on 11/5/26.
//

#pragma once

#include <vector>

#include "Passport.h"

class PassportValidator {
    static constexpr string requiredFields[] = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    [[nodiscard]] static bool isValid(const Passport& passport);
    [[nodiscard]] static bool isValidStrict(const Passport& passport);
    [[nodiscard]] static bool isValidBirthYear(const string& birthYear);
    [[nodiscard]] static bool isValidIssueYear(const string& issueYear);
    [[nodiscard]] static bool isValidExpirationYear(const string& expirationYear);
    [[nodiscard]] static bool isValidHeight(const string& height);
    [[nodiscard]] static bool isValidHairColor(const string& hairColor);
    [[nodiscard]] static bool isValidEyeColor(const string& eyeColor);
    [[nodiscard]] static bool isValidPassportId(const string& passportId);
public:
    [[nodiscard]] static int countValid(const vector<Passport> &passports);

    [[nodiscard]] static int countValidStrict(const vector<Passport> &passports);
};
