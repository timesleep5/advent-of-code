cmake_minimum_required(VERSION 3.20)
project(adventofcode-2020)

set(CMAKE_CXX_STANDARD 20)

add_executable(adventofcode-2020
        main.cpp
        general/Day.cpp
        general/StringManipulation.cpp
        day_01/DayOne.cpp
        day_02/DayTwo.cpp
        day_02/Password.cpp
        general/FileReader.cpp
        general/Input.cpp
)

target_compile_definitions(adventofcode-2020 PRIVATE
        AOC_SOURCE_DIR="${CMAKE_CURRENT_SOURCE_DIR}"
)

set_target_properties(adventofcode-2020 PROPERTIES OUTPUT_NAME main)