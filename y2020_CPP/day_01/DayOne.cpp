#include <iostream>
#include <algorithm>
#include <cstdint>
#include "../general/Day.h"
#include "../general/StringManipulation.h"
  
class DayOne : public Day {
  public:
    DayOne() : Day("day_01/") {
      inputAsInts();
      sortInts();
    };
    string calculateResultPartOne() override;
    string calculateResultPartTwo() override;
    
  private:
    const int TARGET_SUM = 2020;
    const int PIVOT = TARGET_SUM / 2;
    vector<int> ints;
    void inputAsInts();
    void sortInts();
    int pivotIndex();
    bool isPivotIndex(uint16_t index);
};

string DayOne::calculateResultPartOne() {
  uint16_t largeStart = pivotIndex();
  uint16_t smallStart = largeStart-1;
  uint16_t smallNumber;
  uint16_t largeNumber;
  for (uint16_t indexSmall = smallStart; indexSmall >= 0; indexSmall--) {
    smallNumber = ints.at(indexSmall);
    for (uint16_t indexLarge = largeStart; indexLarge < ints.size(); indexLarge++) {
      largeNumber = ints.at(indexLarge);
      uint32_t sum = smallNumber + largeNumber;
      if (sum == TARGET_SUM) {
        return to_string(smallNumber * largeNumber);
      } else if (sum > TARGET_SUM) {
        break;
      }
    }
  }
  return "0";
};

string DayOne::calculateResultPartTwo() {
  if (ints.size() < 3) { 
    return "0";
  }
  uint16_t smallest = ints.at(2);
  uint16_t largest = ints.at(ints.size()-1);
  for (int outerIndex = 0; outerIndex < ints.size()-2; outerIndex++) {
    for (int middleIndex = outerIndex+1; middleIndex < ints.size()-1; middleIndex++) {
      uint16_t outerNumber = ints.at(outerIndex);
      uint16_t middleNumber = ints.at(middleIndex);
      uint32_t intermediateResult = outerNumber + middleNumber;
      if (intermediateResult + smallest > TARGET_SUM || intermediateResult + largest < TARGET_SUM) {
        continue;
      }
      uint16_t low = middleIndex + 1;
      uint16_t high = ints.size() - 1;
      uint16_t mid;
      uint16_t innerNumber;
      uint32_t result;
      while (true) {
        if (low > high) {
          break;
        }
        mid = (high + low) / 2;
        innerNumber = ints.at(mid);
        result = intermediateResult + innerNumber;
        if (result < TARGET_SUM) {
          low = mid + 1;
        } else if (result > TARGET_SUM)  {
          high = mid - 1;
        } else {
          uint32_t largeOuter = outerNumber;
          uint32_t largeMiddle = middleNumber;
          uint32_t largeInner = innerNumber;
          return to_string(largeOuter * largeMiddle * largeInner);
        }
      }
    }
  }
  return "0";
};

void DayOne::inputAsInts() {
  for (string stringEntry: input) {
    StringManipulation::trim(stringEntry);
    int intEntry = stoi(stringEntry);
    ints.push_back(intEntry);
  }
}

void DayOne::sortInts() {
  sort(ints.begin(), ints.end());
}


int DayOne::pivotIndex() {
  uint16_t high = ints.size()-1;
  uint16_t low = 0;
  uint16_t mid;
  while (true) {
    mid = (high+low) / 2;
    if (mid < 1 || mid >= ints.size() || isPivotIndex(mid)) {
      break;
    } else if (ints.at(mid) < PIVOT) {
      low = mid+1;
    } else {
      high = mid-1;
    }
  }
  return mid;
}

bool DayOne::isPivotIndex(uint16_t index) {
  return ints.at(index-1) < PIVOT && ints.at(index) >= PIVOT;
}
    

