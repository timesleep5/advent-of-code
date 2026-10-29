//
// Created by korbinian on 10/29/26.
//

#include "FileReader.h"
#include <fstream>
#include <stdexcept>

Input FileReader::getInput() const {
    string path = ROOT_PATH + "/" + directory + INPUT_FILE;
    ifstream file(path);
    if (!file) {
        throw runtime_error("Could not open input file: " + path);
    }

    string line;
    vector<string> lines;
    while (getline(file, line)) {
        lines.push_back(line);
    }
    Input input{lines};
    return input;
}
