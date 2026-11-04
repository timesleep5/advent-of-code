#include "DayThree.h"


string DayThree::name() const {
    return "Day 3";
}

string DayThree::calculateResultPartOne() {
    Slope slope{.right = 3, .down = 1};
    int treeCount = trajectoryPlanner.countTreesOnTrajectory(slope);
    return to_string(treeCount);
}

string DayThree::calculateResultPartTwo() {
    vector<Slope> slopes = {
        {.right = 1, .down = 1},
        {.right = 3, .down = 1},
        {.right = 5, .down = 1},
        {.right = 7, .down = 1},
        {.right = 1, .down = 2}
    };
    long long product = 1;
    for (Slope slope: slopes) {
        int treeCount = trajectoryPlanner.countTreesOnTrajectory(slope);
        product *= treeCount;
    }
    return to_string(product);
}
