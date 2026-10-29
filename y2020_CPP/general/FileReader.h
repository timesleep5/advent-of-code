//
// Created by korbinian on 10/29/26.
//
#pragma once
#include <string>
#include <utility>
#include "Input.h"
using namespace std;

#ifndef AOC_SOURCE_DIR
#define AOC_SOURCE_DIR "."
#endif


class FileReader {
    const string ROOT_PATH = AOC_SOURCE_DIR;
    const string INPUT_FILE = "input.txt";
    const string directory;

public:
    FileReader(string directory) : directory(std::move(directory)) {
    }

    [[nodiscard]] Input getInput() const;
};
