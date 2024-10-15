#include <vector>
using namespace std;

class ConwayMatrix {
  public: 
    ConwayMatrix(vector<vector<vector<Cube>>> matrix);
    Cube get(int x, int y, int z);

  private:
    vector<vector<vector<Cube>>> matrix;
};

ConwayMatrix::ConwayMatrix(vector<vector<vector<Cube>>> matrix) {
  this->matrix = matrix;
}

Cube ConwayMatrix::get(int x, int y, int z) {
  return matrix.at(x).at(y).at(z);
}

vector<Cube> ConwayMatrix::getNeighbors(int x, int y, int z) {

