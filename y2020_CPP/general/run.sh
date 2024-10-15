#!/bin/bash

DIRECTORY="../$1"

g++ -o $DIRECTORY/main.so $DIRECTORY/main.cpp -std=c++20 

./$DIRECTORY/main.so
