//
// Created by korbinian on 10/29/26.
//

#include "TreeMap.h"

int TreeMap::getWidth() const {
    return input_.read().at(0).size();
}

int TreeMap::getHeight() const {
    return input_.read().size();
}

bool TreeMap::isTree(Position position) const {
    char point = input_.at(position.y, position.x);
    return point == TREE_CHAR;
}
