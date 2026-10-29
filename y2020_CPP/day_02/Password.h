#pragma once

#include <string>

class Password {
public:
    explicit Password(std::string line);

    bool isValid() const;
    bool isValid2() const;

private:
    bool valid;
    bool valid2;
    char policyLetter;
    int minLetterCount;
    int maxLetterCount;
    std::string password;

    void parse(std::string line);
    void validify();
    void validify2();
};
