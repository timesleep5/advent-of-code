#include <algorithm>
#include <cctype>
#include <vector>
using namespace std;

class StringManipulation {
  public:
    static void trim(string &toTrim);
    static vector<string> split(string &toSplit, string &delimiter);
};

void StringManipulation::trim(string &toTrim) {
  toTrim.erase(remove_if(toTrim.begin(), toTrim.end(), ::isspace), toTrim.end());
}

vector<string> StringManipulation::split(string &toSplit, string &delimiter) {
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
