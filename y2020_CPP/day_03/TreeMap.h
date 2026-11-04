//
// Created by korbinian on 10/29/26.
//

#pragma once
#include "Position.h"
#include "../general/Input.h"


class TreeMap {
    const char TREE_CHAR = '#';
    const Input input_;

public:
    TreeMap(const Input &input) : input_(input) {
    }

    [[nodiscard]] int getWidth() const;

    [[nodiscard]] int getHeight() const;

    [[nodiscard]] bool isTree(Position position) const;
};
