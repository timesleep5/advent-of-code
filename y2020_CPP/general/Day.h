#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

class Day {
  public: 
    Day(string directory);
    ~Day();
    void printResultPartOne();
    void printResultPartTwo();
  
  protected:
    vector<string> input;
    virtual string calculateResultPartOne() = 0;
    virtual string calculateResultPartTwo() = 0;

  private:
    const string ROOT_PATH = "/home/korbinian/data/advent-of-code/y2020_CPP/";
    const string INPUT_FILE = "input.txt";
    string directory;
    vector<string> getInput();
};

Day::Day(string directory) {
  this->directory = directory;
  input = getInput();
}

Day::~Day() {}

vector<string> Day::getInput() {
  string path = ROOT_PATH + directory + INPUT_FILE;
  ifstream file(path); 
  string line;
  vector<string> lines;
  while (getline(file, line)) {
    lines.push_back(line);
  }
  
  return lines;
}

void Day::printResultPartOne() {
  string resultPartOne = calculateResultPartOne();
  cout << "Result Part I: " << resultPartOne << endl;
}

void Day::printResultPartTwo() {
  string resultPartTwo = calculateResultPartTwo();
  cout << "Result Part II: " << resultPartTwo << endl;
}
