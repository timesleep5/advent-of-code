//
// Created by korbinian on 10/29/26.
//

#include "TrajectoryPlanner.h"

#include <iostream>

#include "Slope.h"

int TrajectoryPlanner::countTreesOnTrajectory(Slope &slope) const {
    auto positions = calculatePositions(slope.right, slope.down);
    auto treeCount = countTreesOnPositions(positions);
    return treeCount;
}

vector<Position> TrajectoryPlanner::calculatePositions(int right, int down) const {
    vector<Position> positions;
    int width = treeMap.getWidth();
    int height = treeMap.getHeight();
    int y = 0;
    int x = 0;
    while (y < height) {
        Position newPosition{.x = x, .y = y};
        positions.push_back(newPosition);
        y += down;
        x = (x + right) % width;
    }
    return positions;
}

int TrajectoryPlanner::countTreesOnPositions(const vector<Position> &positions) const {
    int treeCount = 0;
    for (const auto &position: positions) {
        if (treeMap.isTree(position)) {
            treeCount++;
        }
    }
    return treeCount;
}
