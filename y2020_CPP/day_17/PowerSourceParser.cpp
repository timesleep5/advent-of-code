#include <vector>
using namespace std;

class PowerSourceParser {
  public:
    PowerSourceParser(vector<string> input);
    vector<vector<vector<Cube>>> getMap();

  private:
    vector<string> input;
    vector<vector<vector<Cube>>> map;
    void parse();
};

PowerSourceParser::PowerSourceParser(vector<string> input) {
  this->input = input;
  parse();
}

vector<vector<vector<Cube>
