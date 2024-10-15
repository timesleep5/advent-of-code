#include <vector>

class Cube {
  public:
    Cube(int x, int y, int z, bool active);
    void activate();
    void deactivate();
    bool isActive();
    vector<Cube> neighbors;

  private:
    int x;
    int y;
    int z;
    bool active;
};

Cube::Cube(int x, int y, int z, bool active) {
  this->x = x;
  this->y = y;
  this->z = z;
  this->active = active;
}

void Cube::activate() {
  active = true;
}

void Cube::deactivate() {
  active = false;
}

bool isActive() {
  return active;
}
