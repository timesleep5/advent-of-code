#!/usr/bin/zsh
set -eu

PROJECT_ROOT="$(cd "$(dirname "$0")" && pwd)"
BUILD_DIR="$PROJECT_ROOT/bin-int"
OUTPUT_DIR="$PROJECT_ROOT/bin"

mkdir -p "$BUILD_DIR"
mkdir -p "$OUTPUT_DIR"

cmake -S "$PROJECT_ROOT" -B "$BUILD_DIR" -G "Unix Makefiles" -DCMAKE_RUNTIME_OUTPUT_DIRECTORY="$OUTPUT_DIR"
cmake --build "$BUILD_DIR"