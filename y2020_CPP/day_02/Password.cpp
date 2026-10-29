#include "Password.h"

#include <vector>

#include "../general/StringManipulation.h"

Password::Password(std::string line) {
    parse(line);
    validify();
    validify2();
}

bool Password::isValid() const {
    return valid;
}

bool Password::isValid2() const {
    return valid2;
}

void Password::parse(std::string line) {
    std::string policyPasswordDelimiter = ": ";
    std::vector<std::string> policyAndPassword = StringManipulation::split(line, policyPasswordDelimiter);
    password = policyAndPassword.at(1);
    std::string policy = policyAndPassword.at(0);
    std::string letterCountsAndLetterDelimiter = " ";
    std::vector<std::string> policyParts = StringManipulation::split(policy, letterCountsAndLetterDelimiter);
    policyLetter = policyParts.at(1)[0];
    std::string letterCounts = policyParts.at(0);
    std::string minMaxLetterCountDelimiter = "-";
    std::vector<std::string> minAndMaxLetterCount = StringManipulation::split(letterCounts, minMaxLetterCountDelimiter);
    std::string minLetterCountString = minAndMaxLetterCount.at(0);
    std::string maxLetterCountString = minAndMaxLetterCount.at(1);
    StringManipulation::trim(minLetterCountString);
    StringManipulation::trim(maxLetterCountString);
    minLetterCount = stoi(minLetterCountString);
    maxLetterCount = stoi(maxLetterCountString);
}

void Password::validify() {
    int counter = 0;
    for (char letter: password) {
        if (letter == policyLetter) {
            counter++;
        }
    }
    valid = minLetterCount <= counter && counter <= maxLetterCount;
}

void Password::validify2() {
    bool switchValid = false;
    int firstIndex = minLetterCount - 1;
    int secondIndex = maxLetterCount - 1;
    if (password[firstIndex] == policyLetter) {
        switchValid = !switchValid;
    }
    if (password[secondIndex] == policyLetter) {
        switchValid = !switchValid;
    }
    valid2 = switchValid;
}
