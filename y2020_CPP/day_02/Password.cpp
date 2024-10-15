#include <vector>
using namespace std;

class Password {
  public:
    Password(string line);
    bool isValid();
    bool isValid2();

  private:
    bool valid;
    bool valid2;
    char policyLetter;
    int minLetterCount;
    int maxLetterCount;
    string password;

    void parse(string line);
    void validify();
    void validify2();
};

Password::Password(string line) {
  parse(line);
  validify();
  validify2();
}

bool Password::isValid() {
  return valid;
}

bool Password::isValid2() {
  return valid2;
}

void Password::parse(string line) {
  string policyPasswordDelimiter = ": ";
  vector<string> policyAndPassword = StringManipulation::split(line, policyPasswordDelimiter);
  password = policyAndPassword.at(1);
  string policy = policyAndPassword.at(0);
  string letterCountsAndLetterDelimiter = " ";
  vector <string> policyParts = StringManipulation::split(policy, letterCountsAndLetterDelimiter);
  policyLetter = policyParts.at(1)[0];
  string letterCounts = policyParts.at(0);
  string minMaxLetterCountDelimiter = "-";
  vector<string> minAndMaxLetterCount = StringManipulation::split(letterCounts, minMaxLetterCountDelimiter);
  string minLetterCountString = minAndMaxLetterCount.at(0);
  string maxLetterCountString = minAndMaxLetterCount.at(1);
  StringManipulation::trim(minLetterCountString);
  StringManipulation::trim(maxLetterCountString);
  minLetterCount = stoi(minLetterCountString);
  maxLetterCount = stoi(maxLetterCountString);
}

void Password::validify() {
  int counter = 0;
  for (char letter: password) {
    if (letter == policyLetter) {
      counter ++;
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
