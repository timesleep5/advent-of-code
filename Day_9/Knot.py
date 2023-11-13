from typing import List, Tuple


class Knot:
    _x: int
    _y: int

    def __init__(self, x: int, y: int):
        self._x = x
        self._y = y

    def difference_to(self, other: "Knot") -> str:
        horizontal = 'R' if other.get_x() > self._x else 'L' if other.get_x() < self._x else ''
        vertical = 'U' if other.get_y() > self._y else 'D' if other.get_y() < self._y else ''

        return horizontal + vertical

    def go_up(self) -> None:
        self._y += 1

    def go_down(self) -> None:
        self._y -= 1

    def go_right(self) -> None:
        self._x += 1

    def go_left(self) -> None:
        self._x -= 1

    def get_x(self) -> int:
        return self._x

    def get_y(self) -> int:
        return self._y

    def get_coordinates(self) -> Tuple[int, int]:
        return self._x, self._y
