import sys
from typing import List

from Day_12.Position import Position
from Day_12.PositionCollection import PositionCollection


class DayTwelve:
    nested_positions: List[List[Position]]
    flat_positions: List[Position]
    inspected_positions: PositionCollection

    start_position: Position
    end_position: Position

    column_length: int
    row_length: int

    def __init__(self, string_lines: List[str]):
        self.nested_positions = self._get_nested_positions(string_lines)
        self.flat_positions = self._get_flat_positions(self.nested_positions)
        self.start_position = self._find_start()
        self.end_position = self._find_end()

        self.inspected_positions = PositionCollection()

        self.column_length = len(self.nested_positions)
        self.row_length = len(self.nested_positions[0])

        sys.setrecursionlimit(2_100_000_000)

    @staticmethod
    def get_input(filename: str) -> List[str]:
        with open(filename, 'r') as file:
            string_lines = file.readlines()

        formatted_string_lines = []
        for line in string_lines:
            formatted_string_lines.append(line.removesuffix('\n'))

        return formatted_string_lines

    def find_shortest_path(self) -> int:
        empty_position = Position(self.start_position.get_x()-1, self.start_position.get_y(), ' ')
        shortest_path = self.inspect_position(self.start_position, empty_position)

        return shortest_path

    def inspect_position(self, position: Position, previous_position: Position) -> int:
        self.inspected_positions.add(position)
        path_lengths = []

        for neighbor_position in position.get_neighbors():
            if position.can_go_to(neighbor_position):
                if neighbor_position.is_end():
                    print('goal!')
                    return 1
                elif neighbor_position != previous_position:
                    path_length = self.inspect_position(neighbor_position, position)
                    if path_length != 0:
                        path_lengths.append(path_length)

        print(path_lengths)

        if path_lengths:
            return min(path_lengths) + 1
        else:
            return 0

    def _within_boundaries(self, x: int, y: int) -> bool:
        return 0 <= x < self.row_length and 0 <= y < self.column_length

    def add_neighbors_to_positions(self) -> None:
        neighbor_pattern = [(-1, 0), (1, 0), (0, -1), (0, 1)]

        for position in self.flat_positions:
            neighbor_positions = [(position.get_x() + x, position.get_y() + y) for x, y in neighbor_pattern]
            for x, y in neighbor_positions:
                if self._within_boundaries(x, y):
                    neighbor = self._get_position(x, y)
                    position.add_neighbor(neighbor)

    @staticmethod
    def _get_flat_positions(nested_positions: List[List[Position]]) -> List[Position]:
        flat_positions = []

        for position_line in nested_positions:
            for position in position_line:
                flat_positions.append(position)

        return flat_positions

    @staticmethod
    def _get_nested_positions(string_lines: List[str]) -> List[List[Position]]:
        nested_positions = []
        position_line = []

        for y_value in range(len(string_lines)):
            for x_value in range(len(string_lines[y_value])):
                letter = string_lines[y_value][x_value]
                position = Position(x_value, y_value, letter)
                position_line.append(position)

            nested_positions.append(position_line)
            position_line = []

        return nested_positions

    def _get_position(self, x: int, y: int) -> Position:
        return self.nested_positions[y][x]

    def _find_start(self) -> Position:
        for position in self.flat_positions:
            if position.is_start():
                return position

    def _find_end(self) -> Position:
        for position in self.flat_positions:
            if position.is_end():
                return position

    def __str__(self) -> str:
        output = 'Positions:\n'

        for position_line in self.nested_positions:
            for position in position_line:
                output += str(position)
                output += ', '

            output += '\n'

        return output
