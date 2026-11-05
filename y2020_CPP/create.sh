#!/usr/bin/env bash
set -euo pipefail

if [[ $# -ne 1 || ! "$1" =~ ^[0-9]+$ ]]; then
    echo "Usage: $0 <day number>" >&2
    exit 1
fi

day=$((10#$1))

if (( day < 1 || day > 99 )); then
    echo "Day must be between 1 and 99." >&2
    exit 1
fi

ones=(
    "" One Two Three Four Five Six Seven Eight Nine Ten
    Eleven Twelve Thirteen Fourteen Fifteen Sixteen Seventeen
    Eighteen Nineteen
)
tens=("" "" Twenty Thirty Forty Fifty Sixty Seventy Eighty Ninety)

if (( day < 20 )); then
    class_suffix="${ones[$day]}"
elif (( day % 10 == 0 )); then
    class_suffix="${tens[$((day / 10))]}"
else
    class_suffix="${tens[$((day / 10))]}${ones[$((day % 10))]}"
fi

day_number=$(printf '%02d' "$day")
directory="day_${day_number}"
class_name="Day${class_suffix}"

if [[ -e "$directory" ]]; then
    echo "Error: $directory already exists." >&2
    exit 1
fi

mkdir "$directory"

cat > "$directory/${class_name}.h" <<EOF
#pragma once

#include "../general/Day.h"

class ${class_name} : public Day {
public:
    ${class_name}() : Day("${directory}/") {
    }

protected:
    string name() const override;

    string calculateResultPartOne() override;

    string calculateResultPartTwo() override;
};
EOF

cat > "$directory/${class_name}.cpp" <<EOF
#include "${class_name}.h"

string ${class_name}::name() const {
    return "Day ${day}";
}

string ${class_name}::calculateResultPartOne() {
    return "";
}

string ${class_name}::calculateResultPartTwo() {
    return "";
}
EOF

: > "$directory/input.txt"
