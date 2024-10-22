from typing import List

from attr import dataclass


@dataclass
class Cave:
    AIR = '.'
    ROCK = '#'
    SAND = 'o'
    SPAWNER = '+'
    ROW_LIMIT = 180  # y range: min 13, max 158
    COL_LIMIT = 400  # x range: min 426, max 506
    COL_BIAS = -300
    SPAWNER_COL = 500 + COL_BIAS
    SPAWNER_ROW = 0

    cave: List[List[str]]

    def set_sand(self, pos: List[int]):
        self.cave[pos[0]][pos[1]] = self.SAND


class CaveParser:
    def __init__(self, input: List[str]) -> None:
        self.input = input
        self.cave = [[Cave.AIR for _ in range(Cave.COL_LIMIT)] for _ in range(Cave.ROW_LIMIT)]
        self.xs = []
        self.ys = []

    def build(self, with_floor: bool = False) -> Cave:
        for line in self.input:
            corners = line.split(' -> ')
            for i in range(len(corners) - 1):
                a = [int(x) for x in corners[i].split(',')]
                b = [int(x) for x in corners[i + 1].split(',')]
                self._build_edge(a, b)
        self._set_sand_spawner()
        if with_floor:
            self._set_floor()
        return Cave(self.cave)

    def _build_edge(self, a: List[int], b: List[int]) -> None:
        a_col, a_row = a
        b_col, b_row = b
        a_col += Cave.COL_BIAS
        b_col += Cave.COL_BIAS
        self.xs += [a_col, b_col]
        self.ys += [a_row, b_row]
        for i in range(min(a_row, b_row), max(a_row, b_row) + 1):
            for j in range(min(a_col, b_col), max(a_col, b_col) + 1):
                self.cave[i][j] = Cave.ROCK

    def _set_sand_spawner(self):
        self.cave[Cave.SPAWNER_ROW][Cave.SPAWNER_COL] = Cave.SPAWNER

    def _set_floor(self):
        highest_rock_row = self._find_highest_rock_row() + 2
        floor_start = [0 - Cave.COL_BIAS, highest_rock_row]
        floor_end = [Cave.COL_LIMIT - 1 - Cave.COL_BIAS, highest_rock_row]
        self._build_edge(floor_start, floor_end)

    def _find_highest_rock_row(self):
        for row_index in range(len(self.cave) - 1, 0, -1):
            if Cave.ROCK in self.cave[row_index]:
                return row_index
