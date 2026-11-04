//
// Created by korbinian on 10/29/26.
//

#pragma once

#include "Slope.h"
#include "TreeMap.h"
#include "../general/Input.h"

class TrajectoryPlanner {
    TreeMap treeMap;

public:
    TrajectoryPlanner(const Input &input) : treeMap(TreeMap(input)) {
    }

    [[nodiscard]] int countTreesOnTrajectory(Slope& slope) const;

private:
    [[nodiscard]] vector<Position> calculatePositions(int right, int down) const;

    [[nodiscard]] int countTreesOnPositions(const vector<Position> &positions) const;
};
