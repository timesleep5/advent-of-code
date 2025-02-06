from typing import List, override

from attr import dataclass

from y2022_Python._general.Day import Day
from y2022_Python.lib.search.search_interfaces import State
from y2022_Python.lib.search.shortest_path import BreadthFirstSearch


class Hill:
    def __init__(self, data: List[str]):
        self.data = data

    def is_goal(self, state: 'HillState') -> bool:
        return self.data[state.row][state.col] == 'E'

    def start_position(self) -> 'HillState':
        for row in range(len(self.data)):
            for col in range(len(self.data[row])):
                if self.data[row][col] == 'S':
                    return HillState(row, col)

    def start_positions(self) -> List['HillState']:
        start_positions = [self.start_position()]
        for row in range(len(self.data)):
            for col in range(len(self.data[row])):
                if self.data[row][col] == 'a':
                    start_positions.append(HillState(row, col))
        return start_positions

    def neighbors(self, state: 'HillState') -> List['HillState']:
        level = self.data[state.row][state.col]
        directions = ((0, 1), (0, -1), (1, 0), (-1, 0))
        new_positions = [(state.row + dir[0], state.col + dir[1]) for dir in directions]
        states = [HillState(pos[0], pos[1]) for pos in new_positions if self.within_boundaries(pos[0], pos[1])]
        climbable_states = [state for state in states if self.climbable(state, level)]
        return climbable_states

    def within_boundaries(self, row: int, col: int) -> bool:
        row_within_boundary = 0 <= row < len(self.data)
        col_within_boundary = 0 <= col < len(self.data[0])
        return row_within_boundary and col_within_boundary

    def climbable(self, state: 'HillState', level: str) -> bool:
        new_level = self.data[state.row][state.col]
        new_level = 'z' if new_level == 'E' else new_level
        level = 'a' if level == 'S' else level
        return ord(new_level) <= ord(level) + 1


hill = Hill(Day().input)


@dataclass(frozen=True)
class HillState(State):
    row: int
    col: int

    def next_states(self) -> List['State']:
        return hill.neighbors(self)

    def is_goal(self) -> bool:
        return hill.is_goal(self)


class DayTwelve(Day):
    def __init__(self):
        super().__init__()
        self.search = BreadthFirstSearch()

    @override
    def result_part_one(self):
        start = hill.start_position()
        path = self.search.search(start)
        number_of_steps = len(path) - 1
        return number_of_steps

    @override
    def result_part_two(self):
        possible_starting_positions = hill.start_positions()
        shortest_path = self.result_part_one()
        for start in possible_starting_positions:
            try:
                path = self.search.search(start)
            except ValueError:
                continue
            number_of_steps = len(path) - 1
            if number_of_steps < shortest_path:
                shortest_path = number_of_steps

        return shortest_path
