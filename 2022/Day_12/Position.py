from typing import List, Tuple


class Position:
    _x: int
    _y: int
    _elevation: int
    _start: bool
    _end: bool

    _neighbors: List["Position"]

    def __init__(self, x: int, y: int, letter: str):
        self._x = x
        self._y = y
        self._start = letter == 'S'
        self._end = letter == 'E'

        if self._start:
            self._elevation = ord('a')
        elif self._end:
            self._elevation = ord('z')
        else:
            self._elevation = ord(letter)

        self._neighbors = []

    def can_go_to(self, other: "Position") -> bool:
        return other._elevation - self._elevation <= 1

    def add_neighbor(self, position: "Position") -> None:
        self._neighbors.append(position)

    def get_neighbors(self) -> List["Position"]:
        return self._neighbors

    def get_x(self) -> int:
        return self._x

    def get_y(self) -> int:
        return self._y

    def is_start(self) -> bool:
        return self._start

    def is_end(self) -> bool:
        return self._end

    def __str__(self) -> str:
        return f'<Position> ({self._x}/{self._y}): {self._elevation}'
