import sys
from typing import List, Tuple

from Day_9.Knot import Knot


# variables: head coordinates, tail coordinates, array of visited positions
# 1. move head
# 2. move tail
# 3. enter tails position if not yet happened


class DayNine:
    knots: List[Knot]
    visited_positions: List[Tuple[int, int]]

    def __init__(self, knot_count: int):
        self.visited_positions = []
        self.knots = []

        for _ in range(knot_count):
            self.knots.append(Knot(0, 0))

        self.visited_positions.append(self.knots[-1].get_coordinates())

    @staticmethod
    def get_input(filename: str) -> List[str]:
        with open(filename, 'r') as file:
            return file.readlines()

    def execute_instructions(self, instructions: List[str]):
        for instruction_line in instructions:
            direction, steps = self._format_line_to_instruction(instruction_line)

            for _ in range(steps):
                self._move(self.knots[0], direction)

                for current_tail_index in range(1, len(self.knots)):
                    current_tail = self.knots[current_tail_index]
                    current_head = self.knots[current_tail_index-1]
                    if current_tail.distance_greater_than_one(current_head):
                        head_directions = current_tail.directions_to(current_head)
                        for head_direction in head_directions:
                            self._move(current_tail, head_direction)

                tail_coordinates = self.knots[-1].get_coordinates()
                if not self._position_already_visited(tail_coordinates):
                    self.visited_positions.append(tail_coordinates)

    def _position_already_visited(self, position: Tuple[int, int]) -> bool:
        for visited_position in self.visited_positions:
            if visited_position == position:
                return True

        return False

    def get_visited_positions_length(self) -> List[Tuple[int, int]]:
        return self.visited_positions

    @staticmethod
    def _move(knot: Knot, direction: str):
        match direction:
            case 'U':
                knot.go_up()
            case 'D':
                knot.go_down()
            case 'R':
                knot.go_right()
            case 'L':
                knot.go_left()
            case _:
                print(f'unknown direction: {direction}')

    @staticmethod
    def _format_line_to_instruction(line: str) -> Tuple[str, int]:
        line = line.removesuffix('\n')
        direction, steps = line.split(' ')
        return direction, int(steps)

    def build_map(self) -> str:
        highest_x = self.find_highest(0)
        lowest_x = self.find_lowest(0)

        highest_y = self.find_highest(1)
        lowest_y = self.find_lowest(1)

        x_dimension = highest_x - lowest_x + 1
        x_bias = -lowest_x

        y_dimension = highest_y - lowest_y + 1
        y_bias = -lowest_y

        empty_map = self.build_empty_map(x_dimension, y_dimension)
        filled_map = self.fill_map(empty_map, x_bias, y_bias)
        map_string = self.map_list_to_string(filled_map)

        return map_string

    @staticmethod
    def build_empty_map(x_dimension: int, y_dimension: int):
        map = []

        for _ in range(y_dimension):
            line = []
            for _ in range(x_dimension):
                line.append('.')
            map.append(line)

        return map

    def find_highest(self, index: int):
        highest = - sys.maxsize
        for coordinates in self.visited_positions:
            if coordinates[index] > highest:
                highest = coordinates[index]

        return highest

    def find_lowest(self, index: int):
        lowest = sys.maxsize
        for coordinates in self.visited_positions:
            if coordinates[index] < lowest:
                lowest = coordinates[index]

        return lowest

    def fill_map(self, map: List[List[str]], x_bias: int, y_bias: int):
        for y in range(len(map)):
            for x in range(len(map[y])):
                for position in self.visited_positions:
                    if position[0] + x_bias == x and position[1] + y_bias == y:
                        if position == (0, 0):
                            map[y][x] = 'S'
                        else:
                            map[y][x] = '#'

        return map

    @staticmethod
    def map_list_to_string(map_list: List[List[str]]) -> str:
        map_string = ''
        for y in range(len(map_list)):
            line = ''.join(map_list[y])
            line += "\n"
            map_string += line

        return map_string
